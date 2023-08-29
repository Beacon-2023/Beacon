package com.BEACON.beacon.fcm;


import lombok.Builder;
import lombok.Getter;



@Builder
@Getter
public class FcmMessage {
    private boolean validate_only;
    private Message message;

    public FcmMessage(boolean validate_only, Message message) {
        this.validate_only = validate_only;
        this.message = message;
    }

    @Builder
    @Getter
    public static class Message {
        private Notification notification;
        private String token;

        public Message(Notification notification, String token) {
            this.notification = notification;
            this.token = token;
        }
    }

    @Builder
    @Getter
    public static class Notification {
        private String title;
        private String body;
        private String image;

        public Notification(String title, String body, String image) {
            this.title = title;
            this.body = body;
            this.image = image;
        }
    }

}
