package com.example.euagenteandroid.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ListView;

import androidx.annotation.Nullable;

import com.example.euagenteandroid.R;
import com.example.euagenteandroid.model.entity.Endemia;
import com.example.euagenteandroid.model.entity.Endereco;
import com.example.euagenteandroid.model.entity.TipoFoco;
import com.example.euagenteandroid.model.util.CheckableItem;
import com.example.euagenteandroid.model.util.CustomAdapter;
import com.example.euagenteandroid.model.util.Validates;
import com.example.euagenteandroid.view.bases.BaseActivity;

import java.util.ArrayList;

import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;

public class ChecklistEnderecoView extends BaseActivity {

    private ListView lv;
    private CustomAdapter customAdapter;
    private Endereco endereco;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_checklist_endereco);
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        lv = findViewById(R.id.list_view);

        findViewById(R.id.checklist_button)
                .setOnClickListener(v -> enviar());

        cadastrarEndereco();
    }

    public void cadastrarEndereco() {
        startActivityForResult(new Intent(this, EnderecoView.class), 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == 2) {
            this.endereco = (Endereco) data.getSerializableExtra("endereco");
            carregarListaTipoFoco();
        }
    }

    private void carregarListaTipoFoco() {
        super.getDaoFacade().getEndemiaDAO().getListFocosByEndemiaId(1, (success, response) -> {
            if (success) {
                setContentInView(criarListaItens((Endemia) response));
            } else {
                makeText(ChecklistEnderecoView.this, R.string.toast_unavailable_service, LENGTH_SHORT).show();
            }
        });
    }

    private ArrayList<CheckableItem> criarListaItens(Endemia endemia) {
        ArrayList<CheckableItem> list = new ArrayList<>();

        for(TipoFoco tp : endemia.getTiposFocos()){
            CheckableItem<TipoFoco> checkableItem = new CheckableItem<>();
            checkableItem.setSelected(false);
            checkableItem.setContent(tp);
            list.add(checkableItem);
        }
        return list;
    }

    private void setContentInView(ArrayList<CheckableItem> checkableItemArrayList) {
        customAdapter = new CustomAdapter(this, checkableItemArrayList);
        lv.setAdapter(customAdapter);
    }

    public void enviar() {
        ArrayList<Integer> listIdSelected = filtrarItensSelecionados();

        if (Validates.checkListEndereco(listIdSelected)) {
            super.getDaoFacade().getChecklistEnderecoDAO().create(endereco.getId(), listIdSelected, (success, response) -> {
                if (success) {
                    showSucesso(this, R.string.checklist_sucesso_titulo);
                    finish();
                } else {
                    makeText(ChecklistEnderecoView.this, R.string.toast_unavailable_service, LENGTH_SHORT).show();
                }
            });
        } else
            makeText(this, R.string.toast_field_not_selected, LENGTH_SHORT).show();
    }

    private ArrayList<Integer> filtrarItensSelecionados() {
        ArrayList<Integer> listIds = new ArrayList<>();
        for (CheckableItem m : customAdapter.getCheckableItemList())
            if(m.getSelected())
                listIds.add(((TipoFoco) m.getContent()).getId());

        return listIds;
    }
}
