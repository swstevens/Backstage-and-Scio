
package fix.v0_14_0

import com.spotify.scio.coders.Coder
import org.apache.avro.generic.GenericRecord
import com.spotify.scio.avro._

object FixAvroCoder5 {

  implicit val c = avroGenericRecordCoder

  def foo[T : Coder]() = ???
  val r = foo[GenericRecord]
}
