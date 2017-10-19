package qelit.magnit_test;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ThirdActivity extends AppCompatActivity {
    float[] ratio = new float[100];
    int a;
    float b;
    ListView lvMain;
    ArrayList<Integer> numb;
    List<Float> rat;
    public static final String PREFS_NAME = "MyPrefsFile";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        //если массивы не созданы, создать
        if (rat == null|| numb == null) {
            numb = new ArrayList<Integer>();
            rat = new ArrayList<Float>();
        }


        //тулбар
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(R.string.settings);

        //получение 2-х массивов перед закрытием
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        int size=settings.getInt("MyArraySize", 0);
        for(int i=0; i < size; i++) {
            numb.add(settings.getInt("saveModeInt" + i, 0));
            rat.add(settings.getFloat("saveModeFloat" + i, 0));
        }
        //адаптер
        lvMain = (ListView) findViewById(R.id.lvMain1);
        SecAdapter adapter = new SecAdapter(this, numb, rat);
        lvMain.setAdapter(adapter);

        ratio = getIntent().getExtras().getFloatArray("rat");

        final EditText et1 = (EditText) findViewById(R.id.et1);
        final EditText et2 = (EditText) findViewById(R.id.et2);
        //обработчик нажатия на кнопку
        Button button = (Button) findViewById(R.id.btn3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a = Integer.parseInt(et1.getText().toString());
                b = Float.parseFloat(et2.getText().toString());
                et1.setText("");
                et2.setText("");
                ratio[a] = b;
                numb.add(a);
                rat.add(b);
                SecAdapter adapter = new SecAdapter(getBaseContext(), numb, rat);
                lvMain.setAdapter(adapter);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void finish(){
        //при закрытии приложения отправляем массив ratio
        Intent intent = new Intent();
        intent.putExtra("rat", ratio);
        setResult(RESULT_OK, intent);
        super.finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // определяем, какая кнопка нажата
        int id = item.getItemId();

        if (id == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop() {
        super.onStop();
        //при закрытии активити, сохраняем массивы
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("MyArraySize", numb.size());
        for (int i = 0; i < numb.size(); i++) {
            editor.putInt("saveModeInt" + i, numb.get(i));
            editor.putFloat("saveModeFloat" + i, rat.get(i));
        }

        editor.commit();
    }
}
