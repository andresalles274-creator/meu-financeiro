package br.com.andre22bodybuilder.blackpharma;
import android.app.*;import android.os.*;import android.content.*;import android.webkit.*;import android.graphics.Color;import java.io.*;import java.nio.charset.StandardCharsets;
public class MainActivity extends Activity{
 WebView w;String backupPayload;static final int EXPORT=41,IMPORT=42;
 @Override public void onCreate(Bundle b){super.onCreate(b);w=new WebView(this);w.setBackgroundColor(Color.rgb(6,6,5));WebSettings s=w.getSettings();s.setJavaScriptEnabled(true);s.setDomStorageEnabled(true);s.setAllowFileAccess(true);w.addJavascriptInterface(new Object(){
 @JavascriptInterface public void exportBackup(String data){backupPayload=data;runOnUiThread(()->{Intent i=new Intent(Intent.ACTION_CREATE_DOCUMENT);i.addCategory(Intent.CATEGORY_OPENABLE);i.setType("application/json");i.putExtra(Intent.EXTRA_TITLE,"black-pharma-backup.json");startActivityForResult(i,EXPORT);});}
 @JavascriptInterface public void importBackup(){runOnUiThread(()->{Intent i=new Intent(Intent.ACTION_OPEN_DOCUMENT);i.addCategory(Intent.CATEGORY_OPENABLE);i.setType("*/*");startActivityForResult(i,IMPORT);});}
 },"BackupAndroid");w.setWebViewClient(new WebViewClient());setContentView(w);if(Build.VERSION.SDK_INT>=21){w.setPadding(0,getStatusBarHeight(),0,0);w.setClipToPadding(false);}w.loadUrl("file:///android_asset/index.html");}
 @Override protected void onActivityResult(int request,int result,Intent data){super.onActivityResult(request,result,data);if(result!=RESULT_OK||data==null||data.getData()==null)return;try{
 if(request==EXPORT){try(OutputStream out=getContentResolver().openOutputStream(data.getData())){out.write(backupPayload.getBytes(StandardCharsets.UTF_8));}runOnUiThread(()->w.evaluateJavascript("alert('Backup salvo com sucesso!')",null));}
 else if(request==IMPORT){ByteArrayOutputStream out=new ByteArrayOutputStream();try(InputStream in=getContentResolver().openInputStream(data.getData())){byte[] buf=new byte[4096];int n;while((n=in.read(buf))!=-1){out.write(buf,0,n);if(out.size()>10000000)throw new IOException("Arquivo muito grande");}}String json=out.toString("UTF-8");String quoted=org.json.JSONObject.quote(json);w.evaluateJavascript("restoreBackup("+quoted+")",null);}
 }catch(Exception e){new AlertDialog.Builder(this).setMessage("Erro no backup: "+e.getMessage()).setPositiveButton("OK",null).show();}}
 private int getStatusBarHeight(){int id=getResources().getIdentifier("status_bar_height","dimen","android");return id>0?getResources().getDimensionPixelSize(id):0;}
 @Override public void onBackPressed(){if(w.canGoBack())w.goBack();else super.onBackPressed();}
}