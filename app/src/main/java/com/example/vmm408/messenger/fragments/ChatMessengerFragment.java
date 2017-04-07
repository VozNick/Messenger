package com.example.vmm408.messenger.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.vmm408.messenger.R;
import com.example.vmm408.messenger.models.MessageModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import butterknife.BindView;
import butterknife.OnClick;

public class ChatMessengerFragment extends BaseFragment implements ValueEventListener {
    @BindView(R.id.new_message_ET)
    EditText new_message_ET;
    @BindView(R.id.message_SV_container)
    LinearLayout message_SV_container;
    MessageModel messageModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_chat_messenger, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initDataBase();
    }

    private void initDataBase() {
        databaseReference = database.getReference("chats");
        databaseReference.orderByChild("createDate");
        databaseReference.addValueEventListener(this);
    }

    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        // за другим разом ошибка null pointer... message_SV...
        if (message_SV_container != null) message_SV_container.removeAllViews();
        Iterator<DataSnapshot> iterator = dataSnapshot.getChildren().iterator();
        while (iterator.hasNext()) {
            messageModel = gson.fromJson(iterator.next().getValue().toString(), MessageModel.class);
            showInChat();
        }
    }

    private void showInChat() {
        if (messageModel.getLoginM().equals(currentUser.getLogin())) {
            message_SV_container.addView(right(style()));
        } else {
            message_SV_container.addView(style());
        }
    }

    private View style() {
        LayoutInflater inflater = (LayoutInflater) getActivity()
                .getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        View view = inflater.inflate(R.layout.style_message, null);
        ((TextView) view.findViewById(R.id.name_TV)).setText(messageModel.getLoginM());
        ((TextView) view.findViewById(R.id.message_TV)).setText(messageModel.getMessage());
        return view;
    }

    private View right(View view) {
        LinearLayout linearLayout = new LinearLayout(getActivity());
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams
                (LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        initLinLayout(linearLayout, params, view);
        return linearLayout;
    }

    private void initLinLayout(LinearLayout layout, LinearLayout.LayoutParams params, View view) {
        layout.setLayoutParams(params);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.addView(content(view));
        layout.addView(imageView(view));
    }

    private LinearLayout content(View view) {
        LinearLayout content = ((LinearLayout) view.findViewById(R.id.texts_LL));
        if (content != null) ((ViewGroup) content.getParent()).removeView(content);
        content.setGravity(Gravity.END);
        return content;
    }

    private View imageView(View view) {
        ImageView imageView = (ImageView) view.findViewById(R.id.image_IV);
        LinearLayout.LayoutParams params1 = (LinearLayout.LayoutParams) imageView.getLayoutParams();
        if (imageView != null) ((ViewGroup) imageView.getParent()).removeView(imageView);
        imageView.setLayoutParams((LinearLayout.LayoutParams) imageView.getLayoutParams());
        imageView.setImageResource(R.mipmap.ic_launcher);
        return imageView;
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {

    }

    @OnClick(R.id.send_IB)
    public void sendMessage() {
        databaseReference.updateChildren(initMap());
        new_message_ET.setText("");
    }

    private HashMap<String, Object> initMap() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put(String.valueOf(new Date()), gson.toJson(newMessageModel()));
        return hashMap;
    }

    private MessageModel newMessageModel() {
        return new MessageModel(currentUser.getLogin(),
                new_message_ET.getText().toString(), new Date());
    }
}
