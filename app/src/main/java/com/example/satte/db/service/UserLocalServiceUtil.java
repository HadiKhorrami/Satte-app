package com.example.satte.db.service;

import android.content.Context;

import com.example.satte.db.tables.User;

import java.util.List;

public class UserLocalServiceUtil {

    private UserLocalService userLocalService;

    public UserLocalServiceUtil(Context context) {
        userLocalService = new UserLocalService(context, com.example.satte.db.helper.SatteDBHelperConfig.class);
    }

    public User insertUser(User user) {
        return userLocalService.createUser(user);
    }

    public User updateUser(User user) {
        return userLocalService.updateUser(user);
    }

    public User getUserById(long userId) {
        return userLocalService.getUserById(userId);
    }

    public List<User> getUsersByScreenName(String screenname,String username) {

        return userLocalService.getUsersByScreenName(screenname,username);
    }

   
    public boolean deleteUser(long userId) {
    return userLocalService.deleteUser(userId);
   }

    /*public Object createOrUpdate(User user) {
        return userLocalService.createOrUpdate(user);
    }*/
}
