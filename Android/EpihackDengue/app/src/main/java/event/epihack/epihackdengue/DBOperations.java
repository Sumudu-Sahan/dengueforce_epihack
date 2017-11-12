package event.epihack.epihackdengue;

import android.net.Uri;
import android.os.StrictMode;
import android.text.Html;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Sumudu on 11/23/2016.
 */

public class DBOperations {
    Data data = new Data();

    public String[][] getAllEmergencyNumbers() { // done
        String
                line = "",
                result = "",

                emergency_number_id = "",
                emergency_number_name = "",
                emergency_number_address = "",
                emergency_number_contact_number1 = "",
                emergency_number_contact_number2 = "",
                emergency_number_contact_number3= "";

        InputStream is;
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            try {
                URL url = new URL(data.getSERVRE_API_PATH_ROOT() + "getAllEmergencyNumbers.php");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                try {
                    conn.connect();
                    is = conn.getInputStream();
                    try {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
                        StringBuilder sb = new StringBuilder();

                        while ((line = reader.readLine()) != null) {
                            sb.append(line + "\n");
                        }
                        result = sb.toString();
                        result = Html.fromHtml(result).toString();
                        is.close();
                        try {
                            JSONArray jArray = new JSONArray(result);
                            int count = jArray.length();

                            for (int i = 0; i < count; i++) {
                                JSONObject jObject = jArray.getJSONObject(i);
                                emergency_number_id += jObject.getString("emergency_number_id") + "#";
                                emergency_number_name += jObject.getString("emergency_number_name") + "#";
                                emergency_number_address += jObject.getString("emergency_number_address") + "#";

                                if(jObject.getString("emergency_number_contact_number1").trim().isEmpty()) {
                                    emergency_number_contact_number1 += "-#";
                                }
                                else{
                                    emergency_number_contact_number1 += jObject.getString("emergency_number_contact_number1") + "#";
                                }

                                if(jObject.getString("emergency_number_contact_number2").trim().isEmpty()) {
                                    emergency_number_contact_number2 += "-#";
                                }
                                else{
                                    emergency_number_contact_number2 += jObject.getString("emergency_number_contact_number2") + "#";
                                }

                                if(jObject.getString("emergency_number_contact_number3").trim().isEmpty()) {
                                    emergency_number_contact_number3 += "-#";
                                }
                                else{
                                    emergency_number_contact_number3 += jObject.getString("emergency_number_contact_number3") + "#";
                                }
                            }

                            return new String[][]{
                                    emergency_number_id.split("#"),
                                    emergency_number_name.split("#"),
                                    emergency_number_address.split("#"),

                                    emergency_number_contact_number1.split("#"),
                                    emergency_number_contact_number2.split("#"),
                                    emergency_number_contact_number3.split("#")
                            };
                        } catch (Exception ex) {
                            System.out.println("Ex " + ex.toString());
                            return null;
                        }
                    } catch (Exception ex) {
                        System.out.println("Ex " + ex.toString());
                        return null;
                    }
                } catch (Exception e) {
                    System.out.println("Ex " + e.toString());
                    return null;
                }
            } catch (Exception ex) {
                System.out.println("Ex " + ex.toString());
                return null;
            }
        } catch (Exception ex) {
            System.out.println("Ex " + ex.toString());
            return null;
        }
    }

    public String[][] getAllHotspots(String userID) { // done
        String
                line = "",
                result = "",

                subscribed_area_id = "",
                hotspot_category_name = "",
                hotspot_name = "",
                hotspot_address = "",
                hotspot_city = "",
                hotspot_state = "",
                hotspot_lat = "",
                hotspot_lng = "";

        InputStream is;
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            try {
                URL url = new URL(data.getSERVRE_API_PATH_ROOT() + "getAllHotspots.php");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);
                Uri.Builder builder = new Uri.Builder()
                        //  .appendQueryParameter("Password", userPassword)
                        .appendQueryParameter("userID", userID);

                String query = builder.build().getEncodedQuery();

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();
                try {
                    conn.connect();
                    is = conn.getInputStream();
                    try {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
                        StringBuilder sb = new StringBuilder();

                        while ((line = reader.readLine()) != null) {
                            sb.append(line + "\n");
                        }
                        result = sb.toString();
                        result = Html.fromHtml(result).toString();

                        System.out.println("result : " + userID);


                        is.close();
                        try {
                            JSONArray jArray = new JSONArray(result);
                            int count = jArray.length();

                            for (int i = 0; i < count; i++) {
                                JSONObject jObject = jArray.getJSONObject(i);
                                subscribed_area_id += jObject.getString("subscribed_area_id") + "#";
                                hotspot_category_name += jObject.getString("hotspot_category_name") + "#";
                                hotspot_name += jObject.getString("hotspot_name") + "#";
                                hotspot_address += jObject.getString("hotspot_address") + "#";
                                hotspot_city += jObject.getString("hotspot_city") + "#";
                                hotspot_state += jObject.getString("hotspot_state") + "#";
                                hotspot_lat += jObject.getString("hotspot_lat") + "#";
                                hotspot_lng += jObject.getString("hotspot_lng") + "#";
                            }

                            return new String[][]{
                                    hotspot_category_name.split("#"),
                                    hotspot_name.split("#"),
                                    hotspot_address.split("#"),

                                    hotspot_city.split("#"),
                                    hotspot_state.split("#"),
                                    hotspot_lat.split("#"),
                                    hotspot_lng.split("#"),
                                    subscribed_area_id.split("#")
                            };
                        } catch (Exception ex) {
                            System.out.println("Ex1 " + ex.toString());
                            return null;
                        }
                    } catch (Exception ex) {
                        System.out.println("Ex2 " + ex.toString());
                        return null;
                    }
                } catch (Exception e) {
                    System.out.println("Ex3 " + e.toString());
                    return null;
                }
            } catch (Exception ex) {
                System.out.println("Ex4 " + ex.toString());
                return null;
            }
        } catch (Exception ex) {
            System.out.println("Ex5 " + ex.toString());
            return null;
        }
    }

    public String[] getLoginDetails (String userName, String userPassword, String token, String latitude, String longitude){
            String
                    line = "",
                    result = "",

                    user_id = "",
                    user_role_id = "",
                    user_first_name = "",
                    user_last_name = "",
                    user_email_address = "",
                    user_gender = "",
                    user_role_name = "";

            InputStream is;
            try {
                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);
                try {
                    URL url = new URL(data.getSERVRE_API_PATH_ROOT() + "getUser.php");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setReadTimeout(15000);
                    conn.setConnectTimeout(15000);
                    conn.setRequestMethod("POST");
                    conn.setDoInput(true);
                    conn.setDoOutput(true);

                    Uri.Builder builder = new Uri.Builder()
                          //  .appendQueryParameter("Password", userPassword)
                            .appendQueryParameter("userPassword", userPassword)
                            .appendQueryParameter("token", token)
                            .appendQueryParameter("userName", userName)

                            .appendQueryParameter("latitude", latitude)
                            .appendQueryParameter("longitude", longitude);

                    String query = builder.build().getEncodedQuery();

                    OutputStream os = conn.getOutputStream();
                    BufferedWriter writer = new BufferedWriter(
                            new OutputStreamWriter(os, "UTF-8"));
                    writer.write(query);
                    writer.flush();
                    writer.close();
                    os.close();
                    try {
                        conn.connect();
                        is = conn.getInputStream();
                        try {
                            BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
                            StringBuilder sb = new StringBuilder();

                            while ((line = reader.readLine()) != null) {
                                sb.append(line + "\n");
                            }
                            result = sb.toString();
                            result = Html.fromHtml(result).toString();
                            is.close();

                            System.out.println("resultresult: " + result);
                            try {
                                JSONArray jArray = new JSONArray(result);
                                int count = jArray.length();
                                for (int i = 0; i < count; i++) {
                                    JSONObject jObject = jArray.getJSONObject(i);

                                    user_id += jObject.getString("user_id");
                                    user_role_id += jObject.getString("user_role_id");
                                    user_role_name  += jObject.getString("user_role_name");
                                    user_first_name += jObject.getString("user_first_name");
                                    user_last_name += jObject.getString("user_last_name");
                                    user_email_address += jObject.getString("user_email_address");

                                    if(jObject.getString("user_gender").equalsIgnoreCase("1")){
                                        user_gender += "Male";
                                    }
                                    else{
                                        user_gender += "Female";
                                    }
                                }
                                return new String[]{user_id, user_role_id, user_role_name, user_first_name, user_last_name, user_email_address, user_gender};
                            } catch (Exception ex) {
                                System.out.println("Ex " + ex.toString());
                                return null;
                            }
                        } catch (Exception ex) {
                            System.out.println("Ex " + ex.toString());
                            return null;
                        }
                    } catch (Exception e) {
                        System.out.println("Ex " + e.toString());
                        return null;
                    }
                } catch (Exception ex) {
                    System.out.println("Ex " + ex.toString());
                    return null;
                }
            } catch (Exception ex) {
                System.out.println("Ex " + ex.toString());
                return null;
            }
        }

    public Boolean signingUp(
            String firstName,
            String lastName,

            String userName,
            String password,

            String email,
            String address,
            String city,
            String state,

            String contactNumber,
            String mobileNumber,

            String token,
            String latitude,
            String longitude) {
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            try {
                URL url = new URL(data.getSERVRE_API_PATH_ROOT() + "signingUp.php");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("firstName", firstName)
                        .appendQueryParameter("lastName", lastName)

                        .appendQueryParameter("userName", userName)
                        .appendQueryParameter("password", password)

                        .appendQueryParameter("email", email)
                        .appendQueryParameter("address", address)
                        .appendQueryParameter("city", city)
                        .appendQueryParameter("state", state)

                        .appendQueryParameter("contactNumber", contactNumber)
                        .appendQueryParameter("mobileNumber", mobileNumber)

                        .appendQueryParameter("token", token)
                        .appendQueryParameter("latitude", latitude)
                        .appendQueryParameter("longitude", longitude);

                String query = builder.build().getEncodedQuery();

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();

                return conn.getResponseCode() == HttpURLConnection.HTTP_OK;
            } catch (Exception ex) {
                System.out.println("Ex " + ex.toString());
                return false;
            }
        } catch (Exception ex) {
            System.out.println("Ex " + ex.toString());
            return false;
        }
    }

    public void uploadSign1(String fileNameAsString) {
        String fileName = fileNameAsString;
        HttpURLConnection conn;
        DataOutputStream dos;
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;
        int maxBufferSize = 1 * 1024 * 1024;
        File sourceFile = null;
        try {
            sourceFile = new File(new Data().getSTORAGE_PATH(), fileName);
            try {
                FileInputStream fileInputStream = new FileInputStream(sourceFile);
                URL url = new URL(data.getSERVRE_API_PATH_ROOT() + "uploadFiles.php");
                conn = (HttpURLConnection) url.openConnection();
                conn.setDoInput(true); // Allow Inputs
                conn.setDoOutput(true); // Allow Outputs
                conn.setUseCaches(false); // Don't use a Cached Copy
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Connection", "close");
                conn.setRequestProperty("ENCTYPE", "multipart/form-data");
                conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
                conn.setRequestProperty("uploaded_file", fileName);

                dos = new DataOutputStream(conn.getOutputStream());

                dos.writeBytes(twoHyphens + boundary + lineEnd);
                dos.writeBytes("Content-Disposition: form-data; name=\"uploaded_file\";filename=\"" + fileName + "\"" + lineEnd);
                dos.writeBytes(lineEnd);
                bytesAvailable = fileInputStream.available();
                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                buffer = new byte[bufferSize];
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);
                while (bytesRead > 0) {
                    dos.write(buffer, 0, bufferSize);
                    bytesAvailable = fileInputStream.available();
                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    bytesRead = fileInputStream.read(buffer, 0, bufferSize);
                }
                dos.writeBytes(lineEnd);
                dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

                // Responses from the server (code and message)
                String serverResponseMessage = conn.getResponseMessage();
                System.out.println(serverResponseMessage + " code is:" + conn.getResponseCode());
                fileInputStream.close();
                dos.flush();
                dos.close();
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        } catch (Exception e) {
            sourceFile = null;
        }
    }

    public void uploadSign2(String fileNameAsString) {
        String fileName = fileNameAsString;
        HttpURLConnection conn;
        DataOutputStream dos;
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;
        int maxBufferSize = 1 * 1024 * 1024;
        File sourceFile = null;
        try {
            sourceFile = new File(new Data().getSTORAGE_PATH(), fileName);
            try {
                FileInputStream fileInputStream = new FileInputStream(sourceFile);
                URL url = new URL(data.getSERVRE_API_PATH_ROOT() + "uploadFiles.php");
                conn = (HttpURLConnection) url.openConnection();
                conn.setDoInput(true); // Allow Inputs
                conn.setDoOutput(true); // Allow Outputs
                conn.setUseCaches(false); // Don't use a Cached Copy
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Connection", "close");
                conn.setRequestProperty("ENCTYPE", "multipart/form-data");
                conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
                conn.setRequestProperty("uploaded_file", fileName);

                dos = new DataOutputStream(conn.getOutputStream());

                dos.writeBytes(twoHyphens + boundary + lineEnd);
                dos.writeBytes("Content-Disposition: form-data; name=\"uploaded_file\";filename=\"" + fileName + "\"" + lineEnd);
                dos.writeBytes(lineEnd);
                bytesAvailable = fileInputStream.available();
                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                buffer = new byte[bufferSize];
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);
                while (bytesRead > 0) {
                    dos.write(buffer, 0, bufferSize);
                    bytesAvailable = fileInputStream.available();
                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    bytesRead = fileInputStream.read(buffer, 0, bufferSize);
                }
                dos.writeBytes(lineEnd);
                dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

                // Responses from the server (code and message)
                String serverResponseMessage = conn.getResponseMessage();
                System.out.println(serverResponseMessage + " code is:" + conn.getResponseCode());
                fileInputStream.close();
                dos.flush();
                dos.close();
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        } catch (Exception e) {
            sourceFile = null;
        }
    }
}