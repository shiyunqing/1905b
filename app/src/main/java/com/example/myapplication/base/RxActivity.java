package com.example.myapplication.base;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.util.SparseArray;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.example.myapplication.R;


public class RxActivity extends AppCompatActivity {


    private SparseArray<OnPermissionResultListener> listenerMap = new SparseArray<>();
    protected AlertDialog.Builder builder;

    public interface OnPermissionResultListener{
        void onAllow();
        void onReject();
    }


    protected void checkPermissions(final String[] permissions,OnPermissionResultListener listener){
        if(Build.VERSION.SDK_INT < 23 || permissions.length == 0){
            if(listener != null){
                listener.onAllow();
            }
        }else{
            int size = listenerMap.size();
            if(listener != null){
                listenerMap.put(size,listener);
            }
            ActivityCompat.requestPermissions(this,permissions,size);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        OnPermissionResultListener onPermissionResultListener = listenerMap.get(requestCode);
        if(onPermissionResultListener != null){
            listenerMap.remove(requestCode);
            for(int i=0; i<grantResults.length; i++){
                if(grantResults[i] == PackageManager.PERMISSION_DENIED){
                    if(!ActivityCompat.shouldShowRequestPermissionRationale(this,permissions[i])){
                        showDialog();
                    }else{
                        onPermissionResultListener.onReject();
                    }
                    return;
                }
            }
            onPermissionResultListener.onAllow();
        }
    }

    private void showDialog(){
        builder = new AlertDialog.Builder(this).setIcon(R.mipmap.ic_launcher).setTitle("设置权限")
                .setMessage("动态权限设置").setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        toAppDetailSetting();
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
    }

    /**
     * 跳转到设置的详情页
     */
    private void toAppDetailSetting(){
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction("android.setting.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.fromParts("package",getPackageName(),null));
        startActivity(intent);
    }
}

