package sa.com.etucook.retrofit

class Markets (val elements: List<MarketPlace>)

class MarketPlace (val lat: Float, val lon: Float, val tags: Tags)

class Tags (val name: String)