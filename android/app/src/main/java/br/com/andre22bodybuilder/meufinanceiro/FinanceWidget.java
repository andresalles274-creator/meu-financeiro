package br.com.andre22bodybuilder.meufinanceiro;
import android.app.*;import android.appwidget.*;import android.content.*;import android.graphics.Color;import android.view.View;import android.widget.*;
public class FinanceWidget extends AppWidgetProvider{
 public void onUpdate(Context c,AppWidgetManager m,int[] ids){for(int id:ids)update(c,m,id);}
 static void update(Context c,AppWidgetManager m,int id){
  RemoteViews v=new RemoteViews(c.getPackageName(),R.layout.finance_widget);android.content.SharedPreferences p=c.getSharedPreferences("widget",0);
  v.setTextViewText(R.id.wBalance,p.getString("balance","R$ 0,00"));v.setTextViewText(R.id.wIn,p.getString("in","R$ 0,00"));v.setTextViewText(R.id.wOut,p.getString("out","R$ 0,00"));v.setTextViewText(R.id.wUpdated,p.getString("updated","Aguardando sync"));
  int[] rows={R.id.wRow1,R.id.wRow2,R.id.wRow3,R.id.wRow4,R.id.wRow5};int[] icons={R.id.wIcon1,R.id.wIcon2,R.id.wIcon3,R.id.wIcon4,R.id.wIcon5};int[] desc={R.id.wDesc1,R.id.wDesc2,R.id.wDesc3,R.id.wDesc4,R.id.wDesc5};int[] meta={R.id.wMeta1,R.id.wMeta2,R.id.wMeta3,R.id.wMeta4,R.id.wMeta5};int[] val={R.id.wValue1,R.id.wValue2,R.id.wValue3,R.id.wValue4,R.id.wValue5};
  for(int i=0;i<5;i++){String s=p.getString("r"+(i+1),"");if(s.isEmpty()){v.setViewVisibility(rows[i],View.GONE);continue;}String[] a=s.split("\\|",-1);boolean in=a.length>0&&"in".equals(a[0]);v.setViewVisibility(rows[i],View.VISIBLE);v.setTextViewText(icons[i],in?"↓":"↗");v.setTextColor(icons[i],Color.parseColor(in?"#55FF91":"#FF6976"));v.setTextViewText(desc[i],a.length>1?a[1]:"");v.setTextViewText(meta[i],a.length>2?a[2]:"");v.setTextViewText(val[i],a.length>3?a[3]:"");v.setTextColor(val[i],Color.parseColor(in?"#55FF91":"#FF6976"));}
  Intent open=new Intent(c,MainActivity.class);PendingIntent po=PendingIntent.getActivity(c,0,open,PendingIntent.FLAG_IMMUTABLE|PendingIntent.FLAG_UPDATE_CURRENT);v.setOnClickPendingIntent(R.id.widgetRoot,po);m.updateAppWidget(id,v);
 }
}