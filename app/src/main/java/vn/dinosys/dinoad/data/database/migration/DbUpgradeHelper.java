package vn.dinosys.dinoad.data.database.migration;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import vn.dinosys.dinoad.data.database.table.dao.DaoMaster;

/**
 * Created by htsi.
 * Since: 7/12/16 on 9:34 AM
 * Project: DinoAd
 */
public class DbUpgradeHelper extends DaoMaster.OpenHelper {

    public DbUpgradeHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(SQLiteDatabase pSQLiteDatabase, int pI, int pI1) {
        // apply db upgrade code here
    }
}
