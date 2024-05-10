def name = 'alice'

// 双引号字符串类似模板字符串，可以对包含的表达式进行计算
def welcome = "${name}, Welcome!"

def multiLineString = '''
1. first
2. second
3. three
4. ${name}
'''

println(name)
println(welcome)
println(multiLineString)

/**
 * 列表默认是:            java.util.ArrayList
 * 可以显示的声明集合类型:  LinkedList list = [1, 2, 3, 4, 5]
 * 也可以使用 as 关键字:   def list =  = [1, 2, 3, 4, 5] as LinkedList
 */
def list = [1,4,5,3,2]
println("list class is ${list.getClass().getName()}, size=${list.size()}")

list.sort()
println("排序:${list}")

list.eachWithIndex { int entry, int i -> {
   println("遍历: index=${i}, value=${entry}")
}  }

// LinkedHashMap
def convertToMap = list.collectEntries { [it.toString(), it]}
println("转换为Map ${convertToMap.getClass().getName()}, 结果=${convertToMap}")


/**
 * 哈希默认是 java.util.LinkedHashMap
 * 访问:
 *  1. colors.red
 *  2. colors[red]
 *  3. colors.get('red')
 * 方法:
 *  size()
 *  isEmpty()
 *  containsKey('key')
 *  containsValue('value')
 *  get()
 *  put('key', 'value')
 *  remove('key')
 */
def colors = [red: 'FF0000', green: '00FF00', blue: '0000FF']
println("hash class is ${colors.getClass().getName()}， size=${colors.size()}")

colors.each {
    println("Map遍历: key=${it.key}, value=${it.value}")
}

/**
 * 闭包
 */
def sayHello = {
    it ->
        println("Hello, ${it}")
}
sayHello.call("小王")

/**
 * 使用安全导航运算符
 */
def user = null
println("获取用户名: ${user?.name}")

assert 1 == 1 : "1不等于2"


/**
 * 文件操作
 */
def file = new File("example.txt")
if (file.exists()) {
    def content = file.text
    println(content)
} else {
    println("File not found.")
}


/**
 * 字符串处理
 */
def str = "apple,banana,orange"
def fruits = str.split(',')
fruits.each { println("字符串处理: ${it}") }


/**
 * JSON处理
 */
import groovy.json.JsonSlurper
import groovy.json.JsonBuilder

// 假设这是从 API 获取的 JSON 字符串
def jsonString = '''
{
  "name": "Alice",
  "age": 30,
  "city": "New York",
  "hobbies": ["reading", "traveling"]
}
'''

// 解析 JSON 字符串为对象
def JSON = new JsonSlurper()
def jsonObject = JSON.parseText(jsonString)

// 打印原始 JSON 对象
println("原始 JSON 对象: \n ${jsonObject}")


// 修改 JSON 对象的属性值
jsonObject.age = 31
jsonObject.city = "Los Angeles"
jsonObject.hobbies.add("cooking")

// 打印修改后的 JSON 对象
println("\n修改后的 JSON 对象: \n ${jsonObject}")

// 将修改后的 JSON 对象转换为 JSON 字符串
def jsonBuilder = new JsonBuilder(jsonObject)
def modifiedJsonString = jsonBuilder.toPrettyString()

// 打印修改后的 JSON 字符串
println("\n修改后的 JSON 字符串: \n ${modifiedJsonString}")


/**
 * 时间日期处理
 */
def now = new Date()
def formattedDate = now.format("yyyy-MM-dd HH:mm:ss")
println("当前日期和时间: $formattedDate")


/**
 * 正则表达式
 */
def str_regex = "The quick brown fox foxfox jumps over the lazy dog"
def replaced = str_regex.replaceAll("\\bfox\\b", "cat")
println("正则表达式: ${replaced}")


/**
 * 网络处理
 */
@Grab('org.codehaus.groovy.modules.http-builder:http-builder:0.7.1')
import groovyx.net.http.RESTClient
import groovy.json.JsonSlurper

def url = 'https://jsonplaceholder.typicode.com/posts/1'
def client = new RESTClient(url)
def response = client.get()

if (response.status == 200) {
    def JSON = new JsonSlurper()
    def data = JSON.parseText(response.getText())
    println("网络处理: ${data}")
} else {
    println("HTTP GET request failed: ${response.status}")
}