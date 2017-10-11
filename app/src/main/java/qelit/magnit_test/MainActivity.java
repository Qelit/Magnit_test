package qelit.magnit_test;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends Activity {
    ListView lvMain;
    Integer[] numbers = new Integer[100];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 0; i < 100; i++){
            numbers[i] = i;
        }

        lvMain = (ListView) findViewById(R.id.lvMain);
        MyAdapter adapter = new MyAdapter(this, numbers);
        lvMain.setAdapter(adapter);
    }
}
