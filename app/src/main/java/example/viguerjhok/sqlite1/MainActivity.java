package example.viguerjhok.sqlite1;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import connecion.Sqlite;

public class MainActivity extends AppCompatActivity {
    ListView Lista;
    EditText edt1, edt2;
    Sqlite CX;
    String[]data;
    String[]id_data;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CX= new Sqlite(this,"bdusuario",null,1);
        edt1=(EditText)findViewById(R.id.edt1);
        edt2=(EditText)findViewById(R.id.edt2);
        Lista=(ListView)findViewById(R.id.listView);
        read_us();

        Lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Delete(id_data[position]);
                Toast.makeText(getApplicationContext(),"Se elimino!!!!",Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void insert_user(View v){
        if (CX.getWritableDatabase()!=null){
        CX.getWritableDatabase().execSQL("INSERT INTO usuario(campo1, campo2) " +
                "values('" + edt1.getText().toString() + "', '" + edt2.getText().toString() + "')");
            limpiar();
        Toast.makeText(this.getApplicationContext(),"insertado", Toast.LENGTH_SHORT).show();
        }
read_us();

    }
    public void read_us(){

         Cursor cur = CX.getReadableDatabase().rawQuery("SELECT id_usuario,campo1 ||' '|| campo2  FROM usuario",null);
        data= new String[ cur.getCount()];
        id_data= new String[ cur.getCount()];
        int n=0;
        if (cur.moveToFirst()) {
            do {
                id_data[n] = cur.getString(0).toString();
                data[n] = cur.getString(1).toString();
                //edt1.setText(cur.getCount());
                n++;
            } while (cur.moveToNext());

        }
       Lista.setAdapter(new ArrayAdapter(this,android.R.layout.simple_list_item_1,data));
    }

    public void Delete(String id){
        CX.getWritableDatabase().delete("usuario","id_usuario='"+id+"'",null);
        CX.close();
        read_us();
    }

    public void limpiar(){
        edt1.setText("");
        edt2.setText("");
    }
}
