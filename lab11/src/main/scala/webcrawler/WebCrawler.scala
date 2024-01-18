package webcrawler
import scala.concurrent._
import ExecutionContext.Implicits.global
import scala.util.{Failure, Success}
import org.htmlcleaner.TagNode

object WebCrawler extends App {

  import scala.io.Source
  import org.htmlcleaner.HtmlCleaner
  import java.net.URL
  

  def depthWalk(element: TagNode, depth: Int): Future[String] = Future{
    val url = element.getAttributeByName("href")
    if (depth > 0 && url.startsWith("http")){
      val rootNode = new HtmlCleaner().clean(new URL(url))
      val elements = rootNode.getElementsByName("a", true)
      elements foreach (element => depthWalk(element, depth - 1).onComplete{
        case Success(value) => println(s"$depth level link = $value")
        case Failure(e) => e.printStackTrace()
      }

      )
    }
    url
  }

  val url = "http://google.com"
  val rootNode = new HtmlCleaner().clean(new URL(url))

  val elements = rootNode.getElementsByName("a", true) 
  elements foreach{element => 
    depthWalk(element, 5).onComplete{
      case Success(value) => println(s"link = $value")
      case Failure(e) => e.printStackTrace()
    }
  }
  Thread.sleep(5000)
  }

