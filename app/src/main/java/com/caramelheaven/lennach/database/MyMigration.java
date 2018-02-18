/*
package com.caramelheaven.lennach.database;

import io.realm.DynamicRealm;
import io.realm.DynamicRealmObject;
import io.realm.FieldAttribute;
import io.realm.RealmList;
import io.realm.RealmMigration;
import io.realm.RealmObjectSchema;
import io.realm.RealmSchema;

public class MyMigration implements RealmMigration {
    @Override
    public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {
        RealmSchema schema = realm.getSchema();

        if (oldVersion == 0) {
            schema.create("BoardDB")
                    .addField("subject", String.class)
                    .addField("comment", String.class)
                    .addField("date", String.class);
            oldVersion++;
        }

        if (oldVersion == 1) {
            RealmObjectSchema fileSchema = schema.create("FileDB")
                    .addField("name", String.class, FieldAttribute.REQUIRED)
                    .addField("displayname", String.class)
                    .addField("fullname", String.class)
                    .addField("height", Integer.class)
                    .addField("md5", String.class)
                    .addField("nsfw", Integer.class)
                    .addField("path", String.class)
                    .addField("size", Integer.class)*/
/**//*

                    .addField("thumbnail", String.class)
                    .addField("tnHeight", Integer.class)
                    .addField("tnWidth", Integer.class)
                    .addField("type", Long.class)
                    .addField("width", Integer.class);
            schema.get("BoardDB")
                    .addRealmListField("files", fileSchema);
            oldVersion++;
        }

        if (oldVersion == 2) {
            RealmObjectSchema postsSchema = schema.create("PostDB")
                    .addField("name", String.class)
                    .addField("num", Integer.class)
                    .addField("number", Integer.class)
                    .addField("op", Integer.class)
                    .addField("parent", String.class)
                    .addField("sticky", Integer.class)
                    .addField("subject", String.class)
                    .addField("tags", String.class)
                    .addField("timestamp", Integer.class)
                    .addField("trip", String.class)
                    .addField("comment", String.class)
                    .addField("date", String.class)
                    .addField("lasthit", Integer.class);
            schema.get("PostDB")
                    .addRealmListField("files", postsSchema);
            oldVersion++;
        }
        if (oldVersion == 3) {
            RealmObjectSchema threadSchema = schema.create("ThreadRealm");
            schema.get("PostDB")
                    .addRealmListField("posts", threadSchema);
        }
    }
}
*/
