
export const searchHotels = async (c) => {
  const city = c.req.query('city');
  const searchService = c.get("searchService");
  const hotels = await searchService.search(city);

  return c.json(hotels, 200);
}

export const updateRoomAvailablity = async (c) => {
  const { hotelId, count } = c.req.query();
  const searchService = c.get("searchService");
  await searchService.updateBookedRoom(hotelId, count);

  return c.json("successfully updated", 200);
}

export const getRoomAvailablity = async (c) => {
  const { rooms, id } = await c.req.query();
  const searchService = c.get("searchService");
  const isAvailable = await searchService.isBookingPossible(id, rooms);

  return c.json(isAvailable, 200);
}

export const getHotelName = async (c) => {
  const id = await c.req.params("id");
  const searchService = c.get("searchService");
  const name = await searchService.getHotelName(id);

  return c.json({ name }, 200);
}