export default class SearchService {
  #searchRepository;
  #cache;

  constructor(searchRepository, redisDb) {
    this.#searchRepository = searchRepository;
    this.#cache = redisDb;
  }

  async search(city) {
    const cachedHotels = await this.#getFromCache(city);

    if (cachedHotels && cachedHotels.length !== 0) {
      console.log(`Cache hit for city: ${city}`);
      return JSON.parse(cachedHotels);
    }

    console.log(`Cache miss for city: ${city}`);

    const hotels = await this.#searchRepository.findAllHotelsByCity(city);

    if (hotels && hotels.length > 0) {
      this.#cache.#setToCache(city, hotels, 3600);
    }

    return hotels;
  }

  async updateBookedRoom(hotelId, count) {
    return await this.#searchRepository.updateRoomCount(hotelId, Number(count));
  }

  async #getFromCache(city) {
    return await this.#cache.get(`hotels:${city}`);
  }

  async #setToCache(city, data, ttlSeconds) {
    const formattedData = JSON.stringify(data);
    await this.#cache.set(
      `hotels:${city}`,
      formattedData,
      { ex: ttlSeconds },
    );
  }
}