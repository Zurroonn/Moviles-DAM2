package com.example.notificaciones;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
Button bt1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        MainActivity main=this;
        bt1=findViewById(R.id.button);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                String channelId = "some_channel_id";
                CharSequence channelName = "Some Channel";
                int importance = NotificationManager.IMPORTANCE_HIGH;
                NotificationChannel notificationChannel = new NotificationChannel(channelId, channelName, importance);
                notificationManager.createNotificationChannel(notificationChannel);
                NotificationCompat.Builder constructorNotif = new NotificationCompat.Builder(main, channelId );
                constructorNotif.setSmallIcon(R.drawable.ic_launcher_foreground);
                constructorNotif.setContentTitle("Mi notificación");
                constructorNotif.setContentText("Has recibido una notificación!!");

                Intent resultadoIntent = new Intent(main, Destino.class);
                TaskStackBuilder pila = TaskStackBuilder.create(main);
                pila.addNextIntentWithParentStack(resultadoIntent);
                PendingIntent resultadoPendingIntent = pila.getPendingIntent(0,PendingIntent.FLAG_IMMUTABLE);

                constructorNotif.setContentIntent(resultadoPendingIntent);
                NotificationCompat.InboxStyle inboxStyle =
                        new NotificationCompat.InboxStyle();
                String[] eventos = new String[5];

                inboxStyle.setBigContentTitle("Notificación expandible:");
                eventos[0]="Esto es la primera línea";
                eventos[1]="Esto es la segunda línea";
                eventos[2]="Esto es la tercera línea";
                eventos[3]="Esto es la cuarta línea";
                eventos[4]="Esto es la quinta línea";

                for (int i=0; i < eventos.length; i++) {
                    inboxStyle.addLine(eventos[i]);
                }
                constructorNotif.setAutoCancel(true);
                constructorNotif.setWhen(0);

                constructorNotif.setStyle(inboxStyle);
                notificationManager.notify(3, constructorNotif.build());


            }
        });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}