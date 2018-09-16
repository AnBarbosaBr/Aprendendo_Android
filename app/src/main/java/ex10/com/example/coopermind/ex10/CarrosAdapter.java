package ex10.com.example.coopermind.ex10;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.coopermind.ex09.R;

import java.util.List;

public class CarrosAdapter extends BaseAdapter {
    private final String TAG  = "CARROS_ADAPTER";
    private Context ctx;
    private List<Carro> carros;

    public CarrosAdapter(Context ctx, List<Carro> carros){
        this.ctx = ctx; this.carros = carros;
    }


    @Override
    public int getCount() {
        return carros.size();
    }

    @Override
    public Object getItem(int position) {
        return carros.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Log.d(TAG, "Obtendo view " + position );
        Carro car = carros.get(position);
        Log.d(TAG, "Carro obtido "+car);

        ViewHolder holder = null;
        if(convertView == null) {
            convertView = LayoutInflater.from(ctx).inflate(R.layout.item_carro, null);
            holder = new ViewHolder();
            holder.imgLogo = (ImageView) convertView.findViewById(R.id.imgLogo);
            holder.txtModelo = (TextView) convertView.findViewById(R.id.txtModelo);
            holder.txtAno = convertView.findViewById(R.id.txtAno);
            holder.txtCombustivel = convertView.findViewById(R.id.txtCombustivel);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }

        Resources resources = ctx.getResources();
        TypedArray logos = resources.obtainTypedArray(R.array.logos);

        holder.imgLogo.setImageDrawable(logos.getDrawable(car.getFabricante()));
        holder.txtAno.setText(String.valueOf(car.getAno()));

        String combustivel = (car.getGasolina()? "G":"")+(car.getEtanol()?"E":"");
        holder.txtCombustivel.setText(combustivel);
        holder.txtModelo.setText(car.getModelo());

        Log.d(TAG, "Retornando view");
        return convertView;

    }

    static class ViewHolder {
        ImageView imgLogo;
        TextView txtModelo;
        TextView txtAno;
        TextView txtCombustivel;
    }
}
