package com.example.recyclerviewtest;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
private List<Msg> msgList =new ArrayList<>();
private EditText inputText;
private Button send;
private RecyclerView msgRecyclerView;
private MsgAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initMsgs(); //初始化消息数据
        inputText =(EditText) findViewById(R.id.input_text);
        send =(Button) findViewById(R.id.send);
        msgRecyclerView =(RecyclerView) findViewById(R.id.msg_recycler_ivew);
        LinearLayoutManager layoutManager= new LinearLayoutManager(this);
        msgRecyclerView.setLayoutManager(layoutManager);
        adapter = new MsgAdapter(msgList);
        msgRecyclerView.setAdapter(adapter);
        send.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String content =inputText.getText().toString();
                if(!"".equals(content)){
                    Msg msg=new Msg(content,Msg.TYPE_SENT);
                    msgList.add(msg);
                    adapter.notifyItemInserted(msgList.size()-1);
                    //当有新消息时刷新RecycleView中的显示
                    msgRecyclerView.scrollToPosition(msgList.size()-1);
                    //将RcycilerView 定位到最后一行
                    inputText.setText(""); //清空输入行内容
                }
            }
        });
    }
    private void initMsgs(){
        Msg msg1=new Msg("Hello guy",Msg.TYPE_RECEIVED);
        msgList.add(msg1);
        Msg msg2=new Msg("Hello Who is that?",Msg.TYPE_SENT);
        msgList.add(msg2);
        Msg msg3=new Msg("This is Tom.Nice talking to you",Msg.TYPE_RECEIVED);
        msgList.add(msg3);
    }
}