package com.operation.dao;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.operation.entity.DataInfoEntity;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.<br/>
 * User: lizhichao<br/>
 * Date: 2018-7-30-0030<br/>
 * Time: 16:33:07<br/>
 * Author:lizhichao<br/>
 * Description: <span style="color:#63D3E9"></span><br/>
 */
public class DataInfoMapper {
    SQLiteDatabase database;

    public DataInfoMapper() {
        String path = "/sdcard/Android/data/com.ha.chao/files/";
        File pathFile = new File(path);
        File file = new File(path + "/datas.db");
        try {
            if (!pathFile.exists()) {
                pathFile.mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        database = SQLiteDatabase.openOrCreateDatabase(file, null);
        try {
            createTable();
        } catch (Exception e) {

        }
    }

    public void install(List<DataInfoEntity> entities) {
        try {
            for (int i = 0; i < entities.size(); i++) {
                if (queryDataById(entities.get(i).getId()).size() > 0) {
                    continue;
                } else {
                    database.execSQL("INSERT INTO datas (id, column1, column2, column3, column4, column5, column6, column7, column8, column9, column10, date) " +
                            "VALUES (" +
                            entities.get(i).getId() + "," +
                            entities.get(i).getColumn1() + "," +
                            entities.get(i).getColumn2() + "," +
                            entities.get(i).getColumn3() + "," +
                            entities.get(i).getColumn4() + "," +
                            entities.get(i).getColumn5() + "," +
                            entities.get(i).getColumn6() + "," +
                            entities.get(i).getColumn7() + "," +
                            entities.get(i).getColumn8() + "," +
                            entities.get(i).getColumn9() + "," +
                            entities.get(i).getColumn10() + ",'" +
                            entities.get(i).getDate() + "')"
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void createTable() {
        String strings[] = new String[]{
                "id",
                "date",
                "column1",
                "column2",
                "column3",
                "column4",
                "column5",
                "column6",
                "column7",
                "column8",
                "column9",
                "column10"
        };
        String sql = "create table  datas (";
        for (int i = 0; i < strings.length; i++) {
            sql += strings[i] + " text  ";
            if (i < strings.length - 1) {
                sql += ",";
            }
        }
        sql += " )";
        try {
            database.execSQL(sql);
        } catch (SQLException e) {

        }
    }

    public List<DataInfoEntity> queryData(String d1, String d2) {
        List<DataInfoEntity> datas = new ArrayList<>();
        String sql = "select t.* from datas  t where t.date >= '" + d1 + "' and t.date <= '" + d2 + "'";
        Cursor cursor = database.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            DataInfoEntity data = new DataInfoEntity();
            data.setId(cursor.getInt(0));
            data.setDate(cursor.getString(1));
            data.setColumn1(cursor.getString(2));
            data.setColumn2(cursor.getString(3));
            data.setColumn3(cursor.getString(4));
            data.setColumn4(cursor.getString(5));
            data.setColumn5(cursor.getString(6));
            data.setColumn6(cursor.getString(7));
            data.setColumn7(cursor.getString(8));
            data.setColumn8(cursor.getString(9));
            data.setColumn9(cursor.getString(10));
            data.setColumn10(cursor.getString(11));
            datas.add(data);
        }
        cursor.close();
        return datas;
    }

    int i=0;
    public List<DataInfoEntity> queryDataById(long id) {
        List<DataInfoEntity> datas = new ArrayList<>();
        String sql = "select t.* from datas  t where t.id=" + id;
        Cursor cursor = database.rawQuery(sql, null);
        i++;
        Log.i("------>data",i+"");
        while (cursor.moveToNext()) {
            DataInfoEntity data = new DataInfoEntity();
            data.setId(cursor.getInt(0));
            data.setDate(cursor.getString(1));
            data.setColumn1(cursor.getString(2));
            data.setColumn2(cursor.getString(3));
            data.setColumn3(cursor.getString(4));
            data.setColumn4(cursor.getString(5));
            data.setColumn5(cursor.getString(6));
            data.setColumn6(cursor.getString(7));
            data.setColumn7(cursor.getString(8));
            data.setColumn8(cursor.getString(9));
            data.setColumn9(cursor.getString(10));
            data.setColumn10(cursor.getString(11));
            datas.add(data);
        }
        cursor.close();
        return datas;
    }
}
