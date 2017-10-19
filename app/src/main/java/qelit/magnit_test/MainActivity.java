package qelit.magnit_test;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity {
    ListView lvMain;
    Integer[] numbers = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,
    28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,
    59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,
    91,92,93,94,95,96,97,98,99};
    float[] ratio = {0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,
            0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,
            0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,
            0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f,0f};
    String save = "SaveRatio";
    public static final String PREFS_NAME = "MyPrefsFile";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //тулбар
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setDisplayShowTitleEnabled(true);

        //загрузка массива ratio
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        for(int i=0; i < ratio.length; i++) {
            ratio[i] = settings.getFloat("saveMode" + i, 0);
        }



        lvMain = (ListView) findViewById(R.id.lvMain);
        //обработчик нажатия на ListView
        lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //открытие второго активити
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("pos",position);
                intent.putExtra("rat",ratio[position]);
                startActivity(intent);
            }
        });
        MyAdapter adapter = new MyAdapter(this, numbers, ratio);
        lvMain.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.settings:
                //открытие третьего активити
                Intent intent = new Intent(MainActivity.this, ThirdActivity.class);
                intent.putExtra("rat",ratio);
                startActivityForResult(intent, 1);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //получение данных из 3-го активити
        if (data == null){
            return;}
        ratio = data.getExtras().getFloatArray("rat");
        MyAdapter adapter = new MyAdapter(this, numbers, ratio);
        lvMain.setAdapter(adapter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        //сохранение массива ratio перед закрытием
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        for (int i = 0; i < ratio.length; i++) {
            editor.putFloat("saveMode" + i, ratio[i]);
        }

        editor.commit();

    }
}
