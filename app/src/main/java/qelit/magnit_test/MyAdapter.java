package qelit.magnit_test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by Qelit on 10.10.2017.
 */

public class MyAdapter extends ArrayAdapter<Integer> {
    private Context context;
    private Integer[] numbers;

    public MyAdapter(Context context, Integer[] numbers){
        super(context, R.layout.line, numbers);
        this.context = context;
        this.numbers = numbers;
    }


    @Override
    public int getCount() {
        return numbers.length;
    }

    @Override
    public Integer getItem(int position) {
        return numbers[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.line, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.tv1);
        textView.setText(String.valueOf(numbers[position]));
        return rowView;
    }
}
