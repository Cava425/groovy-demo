import groovy.json.JsonGenerator
import groovy.json.JsonSlurper


String carRecords = '''
    {
        "records": {
            "car": {
                "name": "HSV Maloo",
                "make": "Holden",
                "year": 2006,
                "price": null,
                "country": "Australia",
                "record": {
                "type": "speed",
                "description": "production pickup truck with speed of 271kph"
                }
            }
        }
    }
'''


def JSON = new JsonSlurper()
def carRecordsObject = JSON.parseText(carRecords)

println(carRecordsObject?.records?.car?.name)

def generator = new JsonGenerator.Options()
        .excludeFieldsByName("country", "record")
        .excludeFieldsByType(Integer)
        .build()

println(generator.toJson(carRecordsObject?.records?.car))