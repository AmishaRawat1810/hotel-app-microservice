export default class SearchService {
  #searchRepository;

  constructor(searchRepository) {
    this.#searchRepository = searchRepository
  }

  async search(city) {
    return await this.#searchRepository.findAllHotelsByCity(city);
  }

  async updateBookedRoom(hotelId, count) {
    return await this.#searchRepository.updateRoomCount(hotelId, Number(count));
  }
}