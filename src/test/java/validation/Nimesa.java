package validation;

import static io.restassured.RestAssured.when;

import java.util.Scanner;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Nimesa {
	Response resp;
	//private static String date="&dt=+25/07/23";
	private static  String API_KEY = "b6907d289e10d714a6e88b30761fae22";
    private static final String BASE_URL = "https://samples.openweathermap.org/data/2.5/forecast/hourly?q=London,us&appid=";
	@Test
	
    public void weather()
	{
	Scanner scanner = new Scanner(System.in);
    int choice;

    do {
        System.out.println(" 1.Get InputDate, 2.Get Wind Speed, 3.Get Pressure, 0.Get Exit");
        System.out.println("Enter your choice: ");
        choice = scanner.nextInt();
        scanner.nextLine(); 

        switch (choice) {
            case 1:
            	System.out.println(getInputDate()+" and its Temp date is "+resp.jsonPath().getString("list[0].dt_txt"));
                break;
            case 2:
        			System.out.println(getwindspeedTime()+" and its Speed date is "+resp.jsonPath().getString("list[0].dt_txt"));
                break;
            case 3:
            	System.out.println(getPressure()+" and its Pressure date is "+resp.jsonPath().getString("list[0].dt_txt"));
                break;
            case 0:
                System.out.println("Exiting the program.");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    } while (choice != 0);
	}  
    public String getwindspeedTime(){
		 resp = when().get(BASE_URL+API_KEY);
		//validation
		String actualdata = resp.jsonPath().getString("list[0].wind.speed");
		System.out.println("wind speed ="+actualdata);
		resp.then().assertThat().statusCode(200).log().all();
		
		return actualdata;
	}
    public String getInputDate(){
   		 resp = when().get(BASE_URL+API_KEY);
   		//validation
   		String actualdata = resp.jsonPath().getString("list[0].dt_txt");
   		System.out.println("date ="+actualdata);
   		resp.then().assertThat().statusCode(200).log().all();
   		
   		return actualdata;
   	}
    public String getPressure(){
   		 resp = when().get(BASE_URL+API_KEY);
   		//validation
   		String actualdata = resp.jsonPath().getString("list[0].main.pressure");
   		System.out.println("pressure ="+actualdata);
   		resp.then().assertThat().statusCode(200).log().all();
   		
   		return actualdata;
   	}
}


