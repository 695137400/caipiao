package com.operation;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.ConsoleMessage;
import android.webkit.WebView;

import com.uzmap.pkg.openapi.ExternalActivity;

public class WebPageModule extends ExternalActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        /**
         * 监听对话框里面的button点击事件
         */
        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case AlertDialog.BUTTON_POSITIVE:// "确认"按钮退出程序
                        finish();
                        break;
                    case AlertDialog.BUTTON_NEGATIVE:// "取消"第二个按钮取消对话框
                        break;
                    default:
                        break;
                }
            }
        };
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            // 创建退出对话框
            AlertDialog isExit = new AlertDialog.Builder(this).create();
            // 设置对话框标题
            isExit.setTitle("提示");
            // 设置对话框消息
            isExit.setMessage("确定要退出吗？");
            // 添加选择按钮并注册监听
            isExit.setButton("确定", listener);
            isExit.setButton2("取消", listener);
            // 显示对话框
            isExit.show();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onConsoleMessage(ConsoleMessage console) {
        super.onConsoleMessage(console);
        System.out.println(console.message());
    }
}
