package com.nowcoder.wenda.async;

import java.util.HashMap;
import java.util.Map;

/***
 * 事件原型 记录现场数据
 */
public class EventModel {
    private EventType type;
    private int actorId;
    private int entityType;
    private int entityId;

    public void setType(EventType type) {
        this.type = type;
    }

    private int senderId;
    private int ownerId;
    private Map<String, String> exts = new HashMap<>();

    public EventModel() {
    }

    public EventModel(EventType type) {
        this.type = type;
    }

    public String getExt(String key) {
        return exts.get(key);
    }

    public EventModel setExt(String key, String value) {
        exts.put(key, value);
        return this;
    }

    public int getActorId() {
        return actorId;
    }

    public EventModel setActorId(int actorId) {
        this.actorId = actorId;
        return this;
    }

    public int getEntityType() {
        return entityType;
    }

    public EventModel setEntityType(int entityType) {
        this.entityType = entityType;
        return this;
    }

    public int getEntityId() {
        return entityId;
    }

    public EventModel setEntityId(int entityId) {
        this.entityId = entityId;
        return this;
    }

    public int getSenderId() {
        return senderId;
    }

    public EventModel setSenderId(int senderId) {
        this.senderId = senderId;
        return this;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public EventModel setOwnerId(int ownerId) {
        this.ownerId = ownerId;
        return this;
    }

    public EventType getType() {
        return type;
    }

    public Map<String, String> getExts() {
        return exts;
    }

    public void setExts(Map<String, String> exts) {
        this.exts = exts;
    }
}
