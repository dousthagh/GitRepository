package dousthagh.software.git.data.model.search.request

data class SearchRequestModel(private val _topic: String) {
    val topic : String
        get() {
            return "topic:$_topic"
        }
}
