export default class SearchRepository {
  #hotels;

  constructor(mongoDb) {
    this.#hotels = mongoDb.collection("hotels");
  }

  async findAllHotelsByCity(city) {
    return await this.#hotels.find({ city }).toArray();
  }

  async updateRoomCount(hotelId, count) {
    return await this.#hotels.updateOne({ hotelId }, { $inc: { roomsBooked: count } })
  }
}