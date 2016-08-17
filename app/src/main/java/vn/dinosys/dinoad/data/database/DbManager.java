package vn.dinosys.dinoad.data.database;

import vn.dinosys.dinoad.data.database.table.dao.DaoSession;
import vn.dinosys.dinoad.data.database.table.dao.UserInfo;

/**
 * Created by huutai.
 * Since: 8/17/16 on 5:34 PM
 * Project: DinoAd
 */
public class DbManager {

    private DaoSession mDaoSession;

    public void saveUser(UserInfo pUserInfo) {
        mDaoSession.getUserInfoDao().insert(pUserInfo);
    }

}
