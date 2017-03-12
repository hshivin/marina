package com.hivin.Params;


public class Dict {



    public final static String[] COLORS ={"#FF0000","#00FF00","#0000FF","#CD00CD","#FFFF00","#FA8072","#D1EEEE","#BF3EFF","#B8860B","#B03060","#8B8B00","#104E8B","#B4EEB4","#8B5742","#8B1C62","#87CEFA","#836FFF","#708090","#DB7093","#FF1493"} ;


    /*
     * 按钮对应值
     */
    public static class FormatDate {
        public static final String YMDHMS = "yyyy-MM-dd HH:mm:ss";
        public static final String YMD = "yyyy-MM-dd";
        public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    }




    public enum OperationStatus {
        STOP(0, "stop"), START(1, "start"), RESTART(2, "restart");
        private int status;
        private String statusV;
        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
             this.status=status;
        }
        public String getStatusV() {
            return statusV;
        }

        public void setStatusV(String statusV) {
             this.statusV=statusV;
        }

        public boolean isRest() {
            return false;
        }

        OperationStatus(int status, String statusV) {
            this.status = status;
            this.statusV = statusV;
        }
    }

}
