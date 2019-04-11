package com.maulani14.utsdaftarcerpen;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteAdapterViewHolder> {


    private ArrayList<Note> note;

    public NoteAdapter(ArrayList<Note> list) {
        this.note= list;
    }

    @Override
    public NoteAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_item, parent, false);
        return new NoteAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NoteAdapterViewHolder holder, final int position) {
        holder.tfJudul.setText(note.get(position).getJudul());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pindahKeNoteDetail(position, v.getContext());
            }
        });

    }

    private void pindahKeNoteDetail(int position, Context c) {
        Context context = c;
        Intent i = new Intent(context, detailNote.class);
        i.putExtra("id", Integer.toString(position));
        i.putExtra("judul", note.get(position).getJudul());
        i.putExtra("sumber", note.get(position).getSumber());
        i.putExtra("isi", note.get(position).getIsi());
        context.startActivity(i);
    }

    @Override
    public int getItemCount() {
        return (note != null) ? note.size() : 0;
    }

    public class NoteAdapterViewHolder extends RecyclerView.ViewHolder{
        private TextView tfJudul;
        //tfNomor
        public NoteAdapterViewHolder(View itemView) {
            super(itemView);
            tfJudul = (TextView) itemView.findViewById(R.id.tfJudul);
        }
    }
}
