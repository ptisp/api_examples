USAGE:

APIRequest req = new APIRequest("http://api.ptisp.pt/domains/ptisp.pt/info", "username", "password");
JSONObject response = req.execute();