package com.operation;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import com.alibaba.fastjson.JSON;
import com.operation.dao.DataInfoMapper;
import com.operation.entity.DataInfoEntity;
import com.uzmap.pkg.uzcore.UZWebView;
import com.uzmap.pkg.uzcore.uzmodule.UZModule;
import com.uzmap.pkg.uzcore.uzmodule.UZModuleContext;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created with IntelliJ IDEA.<br/>
 * User: lizhichao<br/>
 * Date: 2018-7-30-0030<br/>
 * Time: 14:53:26<br/>
 * Author:lizhichao<br/>
 * Description: <span style="color:#63D3E9">数据分析</span><br/>
 */
public class DataInfo extends UZModule {
    DataInfoMapper mapper = new DataInfoMapper();

    public DataInfo(UZWebView webView) {
        super(webView);
    }

    public void jsmethod_byDate(final UZModuleContext context) {
        try {
            CookieSyncManager.createInstance(context.getContext());
            CookieManager cm = CookieManager.getInstance();
            JSONObject object = new JSONObject();
            object.put("cookie", cm.getCookie("https://kfc2000.com"));
            context.success(object, true);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void jsmethod_query(final UZModuleContext context) {
        String d1 = context.optString("d1");
        String d2 = context.optString("d2");

        List<DataInfoEntity> datas = mapper.queryData(d1, d2);
        JSONObject object = new JSONObject();
        try {

            object.put("data", JSON.toJSON(datas));
        } catch (Exception e) {
        }
        context.success(object, true);
    }

    public void jsmethod_save(final UZModuleContext context) {
        new Thread() {
            @Override
            public void run() {
                try {
                    JSONArray datas = context.optJSONArray("data");
                    final JSONObject object = new JSONObject();
                    List<DataInfoEntity> list = new ArrayList<>();
                    if (null != datas && !"".equals(datas)) {
                        for (int i = 0; i < datas.length(); i++) {
                            JSONObject m = (JSONObject) datas.get(i);
                            DataInfoEntity data = new DataInfoEntity();
                            data.setDate(m.get("time").toString());
                            data.setId(Long.parseLong(m.get("num").toString()));
                            JSONArray column = (JSONArray) m.get("column");
                            data.setColumn1(column.getString(0));
                            data.setColumn2(column.getString(1));
                            data.setColumn3(column.getString(2));
                            data.setColumn4(column.getString(3));
                            data.setColumn5(column.getString(4));
                            data.setColumn6(column.getString(5));
                            data.setColumn7(column.getString(6));
                            data.setColumn8(column.getString(7));
                            data.setColumn9(column.getString(8));
                            data.setColumn10(column.getString(9));
                            list.add(data);
                        }
                    }
                    if (list.size() > 0) {
                        mapper.install(list);

                        /*DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case AlertDialog.BUTTON_POSITIVE:// "确认"按钮退出程序
                                        try {
                                            object.put("data", "ok");
                                            context.success(object, true);
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                        break;
                                    case AlertDialog.BUTTON_NEGATIVE:// "取消"第二个按钮取消对话框
                                        break;
                                    default:
                                        break;
                                }
                            }
                        };

                        // 创建退出对话框
                        AlertDialog isExit = new AlertDialog.Builder(context.getContext()).create();
                        // 设置对话框标题
                        isExit.setTitle("提示");
                        // 设置对话框消息
                        isExit.setMessage("数据加载完成");
                        // 添加选择按钮并注册监听
                        isExit.setButton("确定", listener);
                        // 显示对话框
                        isExit.show();*/
                    }
                    Thread.sleep(200);
                    object.put("data", "ok");
                    context.success(object, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.run();
    }
}
