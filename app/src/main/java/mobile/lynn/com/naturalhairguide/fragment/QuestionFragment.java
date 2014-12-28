package mobile.lynn.com.naturalhairguide.fragment;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import mobile.lynn.com.naturalhairguide.R;
import mobile.lynn.com.naturalhairguide.activity.BaseActivity;
import mobile.lynn.com.naturalhairguide.activity.MainActivity;
import mobile.lynn.com.naturalhairguide.helper.Tags;
import mobile.lynn.com.naturalhairguide.listener.QuestionOptionClickListener;
import mobile.lynn.com.naturalhairguide.model.HairType;
import mobile.lynn.com.naturalhairguide.model.QuestionTree;
import mobile.lynn.com.naturalhairguide.model.UserModel;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class QuestionFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_question, container, false);

        QuestionTree hairTypeQuestions = (QuestionTree) getArguments().getSerializable(Tags.QUESTION);
        LinearLayout options = (LinearLayout) rootView.findViewById(R.id.options);
        TextView question = (TextView) rootView.findViewById(R.id.question_text);

        setupButtons(rootView);
        addOptionsToView(inflater, hairTypeQuestions, options, question);

        return rootView;
    }

    private void addOptionsToView(LayoutInflater inflater, QuestionTree hairTypeQuestions, LinearLayout options, TextView question) {
        final List<QuestionTree> optionList = hairTypeQuestions.getOptions();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 20, 0, 0);

        for (QuestionTree option : optionList) {
            Button optionButton = (Button) inflater.inflate(R.layout.layout_secondary_button, null);
            setupOption(option, optionButton);
            options.addView(optionButton, params);
        }

        if (optionList.size() == 0) {
            question.setText(hairTypeQuestions.getTitle());

            String questionnaireType = getArguments().getString(Tags.QUESTIONNAIRE_TYPE);
            Button option1 = (Button) inflater.inflate(R.layout.layout_secondary_button, null);
            Button option2 = (Button) inflater.inflate(R.layout.layout_secondary_button, null);

            if (questionnaireType.equals("type")) {
                String[] split = hairTypeQuestions.getTitle().split(" ");
                final String hairType = split[split.length - 1];
                setupClosingOptionsForHairType(option1, option2, hairType);

                options.addView(option1, params);
                options.addView(option2, params);
            } else if (questionnaireType.equals("checkup")) {
                setupClosingOptionsForHairCheckup(option1);
                options.addView(option1, params);
            }

        } else {
            question.setText(hairTypeQuestions.getTitle());
        }
    }

    private void setupButtons(View rootView) {
        Button skip = (Button) rootView.findViewById(R.id.skip);
        Button restart = (Button) rootView.findViewById(R.id.restart);

        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMainActivity(true);
            }
        });

        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().recreate();
            }
        });
    }

    private void setupClosingOptionsForHairType(Button option1, Button option2, final String hairType) {
        option1.setText("I don't think this is my hair type");
        option1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getActivity().recreate();
            }
        });

        option2.setText("Start Hair Guide!");
        option2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                UserModel currentUser = ((BaseActivity) getActivity()).getCurrentUser();
                currentUser.setHairType(HairType.getHairType(hairType));
                currentUser.save();
                startMainActivity(false);
            }
        });
    }

    private void setupClosingOptionsForHairCheckup(Button option1) {
        option1.setText("Go to application");
        option1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startMainActivity(false);
            }
        });
    }

    private void startMainActivity(boolean skip) {
        Intent intent = new Intent(getActivity(), MainActivity.class);
        intent.putExtra(Tags.SKIP, skip);

        getActivity().startActivity(intent);
        getActivity().finish();
    }

    private void setupOption(QuestionTree questionTree, Button option) {
        option.setText(questionTree.getName());
        option.setOnClickListener(new QuestionOptionClickListener(questionTree, getActivity()));
    }
}
