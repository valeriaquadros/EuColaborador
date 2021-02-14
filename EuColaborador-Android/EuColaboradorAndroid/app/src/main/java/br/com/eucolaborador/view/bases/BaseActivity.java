package br.com.eucolaborador.view.bases;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import br.com.eucolaborador.MainActivity;
import br.com.eucolaborador.R;
import br.com.eucolaborador.model.DaoFacade;
import br.com.eucolaborador.view.ChecklistEnderecoView;
import br.com.eucolaborador.view.DenunciaLocalView;
import br.com.eucolaborador.view.EnfermoView;
import br.com.eucolaborador.view.InformacoesView;
import br.com.eucolaborador.view.LoginView;
import br.com.eucolaborador.view.PerfilView;
import br.com.eucolaborador.view.PrevencoesView;
import br.com.eucolaborador.view.MapaView;
import br.com.eucolaborador.view.SucessoView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.LinkedList;

import co.mobiwise.materialintro.shape.Focus;
import co.mobiwise.materialintro.shape.FocusGravity;
import co.mobiwise.materialintro.shape.ShapeType;
import co.mobiwise.materialintro.view.MaterialIntroView;

public class BaseActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private DaoFacade daoFacade = DaoFacade.getInstance(this);
    private LinkedList<LinkedList<Object>> listTutorial = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addBarTop();
        addBarBottom();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_top, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_residence_verification:
                goToAuth(ChecklistEnderecoView.class);
                return true;
            case R.id.item_option_sick_person:
                goToAuth(EnfermoView.class);
                return true;
            case R.id.item_option_complaint:
                goToAuth(DenunciaLocalView.class);
                return true;
            case R.id.item_option_edit_perfil:
                goToAuth(PerfilView.class);
                return true;
            case R.id.item_option_logout:
                getDaoFacade().getUserDAO().logout();
                goTo(MainActivity.class);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                goTo(MainActivity.class);
                break;
            case R.id.navigation_infos:
                goTo(InformacoesView.class);
                break;
            case R.id.navigation_prevencao:
                goTo(PrevencoesView.class);
                break;
            case R.id.navigation_map:
                goTo(MapaView.class);
                break;
            default:
                break;
        }
        return true;
    }

    protected void goTo(Class desiredDestination) {
        String actualClassName = this.getClass().getSimpleName();
        String desiredClassName = desiredDestination.getSimpleName();
        if (!actualClassName.equalsIgnoreCase(desiredClassName)) {
            Intent intent = getIntent(desiredDestination, desiredClassName);
            startActivity(intent);
            removesActivityTransition();
        }
    }

    private Intent getIntent(Class desiredDestination, String desiredClassName) {
        Intent intent = new Intent(this, desiredDestination);
        if (desiredClassName.equalsIgnoreCase("mainactivity"))
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        return intent;
    }

    protected void goToAuth(Class destiny) {
        if (!getDaoFacade().getUserDAO().isLogged()) {
            Toast.makeText(this, R.string.toast_needed_login, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, LoginView.class);
            intent.putExtra("EXTRA_NEXT_ACTIVITY_CLASS", destiny);
            startActivity(intent);
            removesActivityTransition();
        } else {
            goTo(destiny);
        }
    }

    protected void goToNextActivity() {
        Class nextActivityClass = (Class<Activity>) getIntent().getSerializableExtra("EXTRA_NEXT_ACTIVITY_CLASS");
        Intent intent = new Intent(this, nextActivityClass);
        startActivity(intent);
    }

    private void removesActivityTransition() {
        overridePendingTransition(0, 0);
    }

    public void addBarTop() {
        setSupportActionBar(findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_mosquito);
    }

    public void addBarBottom() {
        ((BottomNavigationView) findViewById(R.id.bottomNavigationView)).setOnNavigationItemSelectedListener(this);
    }

    public DaoFacade getDaoFacade() {
        return daoFacade;
    }

    protected void showSucesso(Context context, int titulo){
        startActivity(new Intent(context, SucessoView.class)
            .putExtra("titulo", titulo));
    }

    protected void showSucessoComSub(Context context, int titulo, int subtitulo){
        startActivity(new Intent(context, SucessoView.class)
            .putExtra("titulo", titulo)
            .putExtra("subtitulo", subtitulo));
    }

    protected void addItemTutorial(int view, int text, String idTutorial) {
        LinkedList<Object> item = new LinkedList<>();
        item.add(findViewById(view));
        item.add(getResources().getText(text));
        item.add(idTutorial);
        listTutorial.add(item);
    }

    protected void showTutorial() {
        LinkedList<Object> item = listTutorial.remove();
        startTutorial((View)item.get(0), (String) item.get(1), (String) item.get(2));
    }

    private void startTutorial(View view, String text, String idTutorial) {
        new MaterialIntroView.Builder(this)
                .setFocusGravity(FocusGravity.CENTER)
                .setFocusType(Focus.MINIMUM)
                .setDelayMillis(500)
                .enableDotAnimation(true)
                .enableIcon(false)
                .enableFadeAnimation(true)
                .setInfoText(text)
                .setShape(ShapeType.RECTANGLE)
                .setTarget(view)
                .setUsageId(idTutorial)
                .setTargetPadding(8)
                .enableDotAnimation(true)
                .performClick(false)
                .setListener(materialIntroViewId -> {
                    if(listTutorial.size() > 0) {
                        LinkedList<Object> item = listTutorial.remove();
                        startTutorial((View)item.get(0), (String) item.get(1), (String) item.get(2));
                    }
                })
                .show();
    }
}
