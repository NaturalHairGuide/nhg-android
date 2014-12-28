package mobile.lynn.com.naturalhairguide.listener;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import java.io.Serializable;

import mobile.lynn.com.naturalhairguide.R;
import mobile.lynn.com.naturalhairguide.fragment.QuestionFragment;
import mobile.lynn.com.naturalhairguide.helper.Tags;
import mobile.lynn.com.naturalhairguide.model.QuestionTree;

public class QuestionOptionClickListener implements View.OnClickListener {
    private final QuestionTree questionTree;
    private final Activity context;

    public QuestionOptionClickListener(QuestionTree questionTree, Activity context) {
        this.questionTree = questionTree;
        this.context = context;
    }

    @Override
    public void onClick(View v) {
        String questionnaireType = context.getIntent().getExtras().getString(Tags.QUESTIONNAIRE_TYPE);

        Bundle args = new Bundle();
        args.putSerializable(Tags.QUESTION, questionTree);
        args.putSerializable(Tags.QUESTIONNAIRE_TYPE, questionnaireType);

        QuestionFragment nextFragment = new QuestionFragment();
        nextFragment.setArguments(args);
        context.getFragmentManager().beginTransaction()
                .replace(R.id.question_frame, nextFragment).commit();
    }
}
