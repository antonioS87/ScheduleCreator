package com.example.schedulecreator.Database;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

public class Migrations {

    public static Migration[] getMigrations(){
        Migration[] migrations = {
                new Migration(1,2) {
                    @Override
                    public void migrate(@NonNull SupportSQLiteDatabase database) {


                    }
                }
        };

        return migrations;
    }
}
