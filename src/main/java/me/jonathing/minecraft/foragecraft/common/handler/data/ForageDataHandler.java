package me.jonathing.minecraft.foragecraft.common.handler.data;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Pair;
import me.jonathing.minecraft.foragecraft.info.ForageInfo;
import net.minecraft.client.resources.JsonReloadListener;
import net.minecraft.profiler.IProfiler;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class ForageDataHandler<K, V> extends JsonReloadListener
{
    protected static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
    public static final Logger LOGGER = LogManager.getLogger();
    protected final String name;
    protected Map<K, V> data = Maps.newHashMap();

    public ForageDataHandler(String name)
    {
        super(GSON, ForageInfo.MOD_ID + "/" + name);
        this.name = name;
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> jsonMap, @Nonnull IResourceManager resourceManager, @Nonnull IProfiler profiler)
    {
        Map<K, V> newDataMap = Maps.newHashMap();
        Set<Map.Entry<ResourceLocation, JsonElement>> dataSet = jsonMap.entrySet();

        for (Map.Entry<ResourceLocation, JsonElement> entry : dataSet)
        {
            ResourceLocation name = entry.getKey();

            try
            {
                Pair<K, V> parsedData = parseJson(entry.getValue().getAsJsonObject(), name);
                this.registerData(newDataMap, parsedData.getFirst(), parsedData.getSecond());
            }
            catch (MissingRegistryObjectException e)
            {
                LOGGER.error(e);
            }
            catch (Exception e)
            {
                LOGGER.error("Parsing error loading {}: {}", this.name, name, e);
            }
        }

        this.data = newDataMap;
        LOGGER.info("Loaded {} {}", newDataMap.size(), this.name);
    }

    protected abstract Pair<K, V> parseJson(JsonObject json, ResourceLocation name) throws MissingRegistryObjectException;

    protected void registerData(Map<K, V> activeData, K key, V value)
    {
        activeData.put(key, value);
    }

    public Map<K, V> getData()
    {
        return this.data;
    }

    public V getValue(K key)
    {
        return this.data.get(key);
    }

    public static class MissingRegistryObjectException extends Exception
    {
        public MissingRegistryObjectException(String message)
        {
            super(message);
        }
    }
}
