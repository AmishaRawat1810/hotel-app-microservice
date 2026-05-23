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

  async checkBooking(hotelId, roomsToBook) {
    const hotel = await this.#hotels.findOne({ _id: hotelId });
    if (hotel && hotel.totalRooms >= roomsToBook) {
      return true;
    }
    return false;
  }

  async searchHotel(hotelId) {
    const hotel = await this.#hotels.findOne({ _id: hotelId });
    return hotel?.name;
  }
}