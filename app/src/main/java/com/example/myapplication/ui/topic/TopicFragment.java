package com.example.myapplication.ui.topic;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.myapplication.R;

public class TopicFragment extends Fragment {

    private TopicViewModel topicViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        topicViewModel =
                ViewModelProviders.of(this).get(TopicViewModel.class);
        View root = inflater.inflate(R.layout.fragment_topic, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        topicViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}