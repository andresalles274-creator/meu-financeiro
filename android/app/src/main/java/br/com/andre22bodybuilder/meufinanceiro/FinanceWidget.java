package br.com.andre22bodybuilder.meufinanceiro;
import android.app.*;import android.appwidget.*;import android.content.*;import android.widget.*;
public class FinanceWidget extends AppWidgetProvider{
 public void onUpdate(Context c,AppWidgetManager m,int[] ids){for(int id:ids)update(c,m,id);}
 static void update(Context c,AppWidgetManager m,int id){
  RemoteViews v=new RemoteViews(c.getPackageName(),R.layout.finance_widget);
  android.content.SharedPreferences p=c.getSharedPreferences("widget",0);
  v.setTextViewText(R.id.wBalance,p.getString("balance","R$ 0,00"));
  v.setTextViewText(R.id.wIn,"Entrou hoje\n"+p.getString("in","R$ 0,00"));
  v.setTextViewText(R.id.wOut,"Saiu hoje\n"+p.getString("out","R$ 0,00"));
  v.setTextViewText(R.id.wRecent,p.getString("recent","Abra o app para sincronizar"));
  Intent open=new Intent(c,MainActivity.class);
  v.setOnClickPendingIntent(R.id.wBalance,PendingIntent.getActivity(c,0,open,PendingIntent.FLAG_IMMUTABLE|PendingIntent.FLAG_UPDATE_CURRENT));
  Intent open=new Intent(c,MainActivity.class);
  PendingIntent po=PendingIntent.getActivity(c,0,open,PendingIntent.FLAG_IMMUTABLE|PendingIntent.FLAG_UPDATE_CURRENT);
  v.setOnClickPendingIntent(R.id.wBalance,po);
  v.setOnClickPendingIntent(R.id.wRecent,po);
  m.updateAppWidget(id,v);
 }
}