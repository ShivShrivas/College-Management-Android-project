package com.techshiv.collegeapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class AdapterLetestNotification extends FirebaseRecyclerAdapter<notiificationDatamodel,AdapterLetestNotification.NotificatationHolder> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public AdapterLetestNotification(@NonNull FirebaseRecyclerOptions<notiificationDatamodel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull NotificatationHolder holder, int position, @NonNull notiificationDatamodel model) {
        holder.messageNotificationStud.setText(model.getNotificationMessage());
        holder.titleNotificationStud.setText(model.getNotificationTitle());
        holder.timedatenewssnotification.setText(model.getDatetime());
        Student_notifiaction_fragment.progressDialog.dismiss();

    }

    @NonNull
    @Override
    public NotificatationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.studentnotificationcard,parent,false);
        return new NotificatationHolder(view);
    }
    @Override
    public notiificationDatamodel getItem(int position) {
        return super.getItem(getItemCount() - 1 - position);
    }
    public class NotificatationHolder extends RecyclerView.ViewHolder {
        TextView titleNotificationStud,messageNotificationStud,timedatenewssnotification;
        public NotificatationHolder(@NonNull View itemView) {
            super(itemView);
            titleNotificationStud=itemView.findViewById(R.id.titleNotificationStud);
            messageNotificationStud=itemView.findViewById(R.id.messageNotificationStud);
            timedatenewssnotification=itemView.findViewById(R.id.timedatenewssnotification);
        }
    }
}
