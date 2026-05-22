export default class SearchRepository {
  #hotels;
  constructor(db) {
    this.#hotels = db.collection("hotels");
  }

  async findAllHotelsByCity(city) {
    return await this.#hotels.find({ city }).toArray();
  }

  async updateRoomCount(hotelId, count) {
    return await this.#hotels.updateOne({ hotelId }, { $inc: { roomsBooked: count } })
  }
}