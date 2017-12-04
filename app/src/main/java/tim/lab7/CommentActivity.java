package tim.lab7;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import tim.lab7.rest.RestClient;
import tim.lab7.rest.model.CommentPOJO;
import tim.lab7.rest.model.EntryPOJO;

@EActivity(R.layout.activity_comment)
public class CommentActivity extends AppCompatActivity {

    @ViewById
    EditText urlText;

    @ViewById
    EditText idText;
    @ViewById
    EditText dateText;
    @ViewById
    EditText userText;
    @ViewById
    EditText subjectText;
    @ViewById
    EditText contentText;

    @ViewById
    EditText addCommentIdText;
    @ViewById
    EditText delCommentEntryIdText;
    @ViewById
    EditText delCommentIdText;

    @Click
    void addCommentButton(){
        addComment();
    }

    @Click
    protected void delCommentButton(){
        delComment();
    }

    @Background
    void addComment(){
        RestClient client = new RestClient(urlText.getText().toString());
        final CommentPOJO commentPOJO = new CommentPOJO(Integer.parseInt(idText.getText().toString()),dateText.getText().toString(),userText.getText().toString(),subjectText.getText().toString(),contentText.getText().toString());
        client.addComment(addCommentIdText.getText().toString(),commentPOJO);
    }

    @Background
    void delComment(){
        RestClient client = new RestClient(urlText.getText().toString());
        client.delComment(delCommentEntryIdText.getText().toString(), delCommentIdText.getText().toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}
