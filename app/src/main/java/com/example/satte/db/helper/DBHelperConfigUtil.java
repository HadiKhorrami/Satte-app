package com.example.satte.db.helper;

import com.example.satte.db.tables.User;
import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

import java.io.IOException;
import java.sql.SQLException;

import com.example.satte.db.tables.Project;
import com.example.satte.db.tables.ExecutionProjectInspection;

public class DBHelperConfigUtil extends OrmLiteConfigUtil {

    private static final Class<?>[] classes = new Class<?>[]{Project.class, ExecutionProjectInspection.class, User.class};

    public static void main(String[] args) throws SQLException, IOException {

        writeConfigFile("ormlite_config.txt", classes);
    }
}
