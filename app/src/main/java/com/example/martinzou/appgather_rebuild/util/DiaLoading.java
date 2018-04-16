package com.example.martinzou.appgather_rebuild.util;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.martinzou.appgather_rebuild.R;
import com.example.martinzou.appgather_rebuild.view.CustomDialog;

/**
 * Created by 10630 on 2018/3/27.
 */

public class DiaLoading {
    private CustomDialog dialog;
    private LayoutInflater mInflater;
    private TextView txtContent;

    public DiaLoading(Context context) {
        //就是将LayOut转换位VIew对象
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        init(context);
        //好像只能通过这种方式来进行实例化
    }

    private void init(Context context) {
        View digView=mInflater.inflate(R.layout.dialog_loading,null);
        //实例化这个界面中的部件
        txtContent=(TextView)digView.findViewById(R.id.tv_dialogContent);
        dialog=new CustomDialog(context).setContentView(digView,Gravity.CENTER).setCanceledOnTouchOutside(false).setCancelable(true);

    }

    public void show(){dialog.show();}

    public void dismiss(){dialog.dismiss();}

    public CustomDialog getDialog(){return dialog;}

    public TextView getTxtContent(){return txtContent;}


}
