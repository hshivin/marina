package com.hivin.configure;

/**
 * Created by
 *
 * @auth:hivin
 * @date:17/2/17
 */
public class DatabaseContextHolder {

    private static final ThreadLocal<DatabaseType> contextHolder = new ThreadLocal<>();

    public static void setDatabaseType(DatabaseType type) {
        contextHolder.set(type);
    }

    public static DatabaseType getDatabaseType() {
        return contextHolder.get();
    }

    public static void clear() {
        contextHolder.remove();
    }

}
