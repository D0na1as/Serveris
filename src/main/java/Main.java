import com.byteowls.jopencage.JOpenCageGeocoder;
import com.byteowls.jopencage.model.JOpenCageForwardRequest;
import com.byteowls.jopencage.model.JOpenCageLatLng;
import com.byteowls.jopencage.model.JOpenCageResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;

import java.util.List;

public class Main {


    public static void main(String[] args) throws Exception {

        Database item = new Database("heroku_47fd00a889de629");
        PassCoding code = new PassCoding();
        JOpenCageGeocoder jOpenCageGeocoder = new JOpenCageGeocoder("d3e798fb71524178afd780d87f98c2bc");

//        Database item = new Database("dbaze");
//        PassCoding code = new PassCoding();
//        JOpenCageGeocoder jOpenCageGeocoder = new JOpenCageGeocoder("d3e798fb71524178afd780d87f98c2bc");

        Spark.port(80);

        Spark.get("/getNodes", new Route() {
            @Override
            public JSONArray handle(Request request, Response response) throws Exception {
                List<Recipient> nodes = item.getNodes();
                JSONArray array = new JSONArray();
                System.out.println("Prisijungta");
                if (nodes != null) {
                    for (Recipient node : nodes) {

                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("company_name", node.getCompany_name());
                        jsonObject.put("id", node.getId());
                        jsonObject.put("latitude", node.getLatitude());
                        jsonObject.put("longitude", node.getLongitude());
                        jsonObject.put("priority", node.getPriority());
                        array.put(jsonObject);
                    }
                    return array;
                } else {
                    response.status(400);
                    return null;
                }
            }
        });
        Spark.get("/getStatistic", new Route() {
            @Override
            public JSONObject handle(Request request, Response response) throws Exception {
                Statistic data = item.getStatistic();
                if (data != null) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("caps", data.getCaps());
                    jsonObject.put("gloves", data.getGloves());
                    jsonObject.put("goggles", data.getGoggles());
                    jsonObject.put("masks", data.getMasks());
                    jsonObject.put("shoe_covers", data.getShoe_covers());
                    jsonObject.put("suits", data.getSuits());
                    return jsonObject;
                } else {
                    response.status(400);
                    return null;
                }
            }
        });
        Spark.get("/getCompanies", new Route() {
            @Override
            public JSONArray handle(Request request, Response response) throws Exception {
                List<Company> companies = item.getCompanies();
                JSONArray array = new JSONArray();
                if (companies != null) {
                    for (Company single : companies) {

                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("id", single.getId());
                        jsonObject.put("company_name", single.getCompany_name());
                        jsonObject.put("email", single.getEmail());
                        jsonObject.put("ac_person", single.getAc_person());
                        jsonObject.put("phone", single.getPhone());
                        jsonObject.put("street", single.getStreet());
                        jsonObject.put("house_nr", single.getHouse_nr());
                        jsonObject.put("city", single.getCity());
                        jsonObject.put("caps", single.getCaps());
                        jsonObject.put("gloves", single.getGloves());
                        jsonObject.put("goggles", single.getGoggles());
                        jsonObject.put("masks", single.getMasks());
                        jsonObject.put("shoe_covers", single.getShoe_covers());
                        jsonObject.put("suits", single.getSuits());
                        jsonObject.put("priority", single.getPriority());
                        array.put(jsonObject);
                    }
                    return array;
                } else {
                    response.status(400);
                    return null;
                }
            }
        });
        Spark.post("/login", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {

                String email = request.queryParams("email");
                String password = code.encrypt(request.queryParams("password"));
                String resp = item.getString("email", email);
                JSONObject jsonObject = new JSONObject();

                if (resp != null) {
                    Recipient recipient = item.getRecipient(email, password);
                    if (recipient != null) {
                        jsonObject.put("id", recipient.getId());
                        System.out.println(recipient.getId());
                        jsonObject.put("email", recipient.getEmail());
                        jsonObject.put("company_name", recipient.getCompany_name());
                        jsonObject.put("ac_person", recipient.getAc_person());
                        jsonObject.put("phone", recipient.getPhone());
                        jsonObject.put("street", recipient.getStreet());
                        jsonObject.put("house_nr", recipient.getHouse_nr());
                        jsonObject.put("city", recipient.getCity());
                        jsonObject.put("caps", recipient.getCaps());
                        jsonObject.put("gloves", recipient.getGloves());
                        jsonObject.put("goggles", recipient.getGoggles());
                        jsonObject.put("masks", recipient.getMasks());
                        jsonObject.put("shoe_covers", recipient.getShoe_covers());
                        jsonObject.put("suits", recipient.getSuits());
                        jsonObject.put("priority", recipient.getPriority());
                    } else {
                        jsonObject.put("id", 0);
                    }
                    return jsonObject;
                } else {
                    response.status(400);
                    return null;
                }
            }
        });
        Spark.post("/updateRecipient", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {

                int id = Integer.parseInt(request.queryParams("id"));
                String email = request.queryParams("email");
                String password = code.encrypt(request.queryParams("password"));
                String company_name = request.queryParams("company_name");
                String ac_person = request.queryParams("ac_person");
                String phone = request.queryParams("phone");
                int caps = Integer.parseInt(request.queryParams("caps"));
                int gloves = Integer.parseInt(request.queryParams("gloves"));
                int goggles = Integer.parseInt(request.queryParams("goggles"));
                int masks = Integer.parseInt(request.queryParams("masks"));
                int shoe_covers = Integer.parseInt(request.queryParams("shoe_covers"));
                int suits = Integer.parseInt(request.queryParams("suits"));
                int priority = Integer.parseInt(request.queryParams("priority"));

                Recipient recipientCheck = item.getRecipient(email, password);
                JSONObject jsonObject = new JSONObject();

                if (recipientCheck != null) {
                    item.updateRecipient(id, "password", password);
                    item.updateRecipient(id, "company_name", company_name);
                    item.updateRecipient(id, "ac_person", ac_person);
                    item.updateRecipient(id, "phone", phone);
                    item.updateRecipient(id, "caps", caps);
                    item.updateRecipient(id, "gloves", gloves);
                    item.updateRecipient(id, "masks", masks);
                    item.updateRecipient(id, "goggles", goggles);
                    item.updateRecipient(id, "shoe_covers", shoe_covers);
                    item.updateRecipient(id, "suits", suits);
                    item.updateRecipient(id, "priority", priority);

                    return "SUCCESS";
                } else {
                    response.status(400);
                    return null;
                }
            }
        });
        Spark.post("/registerRecipient", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                String email = request.queryParams("email");
                //Uzkoduoja spaltazodi
                String password = code.encrypt(request.queryParams("password"));
                String company_name = request.queryParams("company_name");
                String ac_person = request.queryParams("ac_person");
                String phone = request.queryParams("phone");
                String street = request.queryParams("street");
                int house_nr = Integer.parseInt(request.queryParams("house_nr"));
                String city = request.queryParams("city");
                double longitude = Double.parseDouble(request.queryParams("longitude"));
                double latitude = Double.parseDouble(request.queryParams("latitude"));

                Recipient recipient = new Recipient(password, email, company_name, ac_person, phone,
                        street, house_nr, city, latitude, longitude);
                item.registerRecipient(recipient);
                return "SUCCESS";
            }
        });
        Spark.post("/getLocation", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                String street = request.queryParams("street");
                int house_nr = Integer.parseInt(request.queryParams("house_nr"));
                String city = request.queryParams("city");
                String query = street + " " + house_nr + ", " + city;

                JOpenCageForwardRequest requestLocation = new JOpenCageForwardRequest(query);
                requestLocation.setRestrictToCountryCode("lt"); // restrict results to a specific country

                JOpenCageResponse responseLocation = jOpenCageGeocoder.forward(requestLocation);
                JOpenCageLatLng LatLng = responseLocation.getFirstPosition(); // get the coordinate pair of the first result

                JSONObject json = new JSONObject();
                try {
                    json.put("latitude", responseLocation.getFirstPosition().getLat());
                    System.out.println("latitude: " + responseLocation.getFirstPosition().getLat());
                    json.put("longitude", responseLocation.getFirstPosition().getLng());
                    System.out.println("longitude" + responseLocation.getFirstPosition().getLng());
                    return json;
                } catch (NullPointerException e) {
                    System.out.println("Location null");
                    e.printStackTrace();
                    return null;
                }
            }
        });
        Spark.post("/checkEmail", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                String email = request.queryParams("email");

                JSONObject json = new JSONObject();
                try {
                    String resp = item.getString("email", email);
                    if (resp != null) {
                        json.put("email", resp);
                        return json;
                    } else {
                        return null;
                    }
                } catch (NullPointerException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        });
        Spark.post("/reset", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                String email = request.queryParams("email");
                JSONObject json = new JSONObject();
                try {
                    String resp = item.getString("email", email);

                    if (resp != null) {

                        String randomWord = code.randomString();
                        int id = item.getInt("id", email);

                        try {

                            String encryptedPass = code.encrypt(randomWord);
                            item.updateRecipient(id, "password", encryptedPass);
                            SendEmail mail = new SendEmail(email, randomWord);
                            mail.send();
                            return "Changed";

                        } catch (Exception e) {

                            e.printStackTrace();

                            return null;
                        }
                    } else {
                        return null;
                    }
                } catch (NullPointerException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        });
        Spark.post("/delete", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                int id = Integer.parseInt(request.queryParams("id"));
                JSONObject json = new JSONObject();
                try {
                    item.deleteRecipient(id) ;
                    return "SUCCESS";
                } catch (NullPointerException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        });
    }

}
