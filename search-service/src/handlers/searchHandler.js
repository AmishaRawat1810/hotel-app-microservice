
export const searchHotels = async (ctx) => {
  const city = ctx.req.query('city');
  const searchService = ctx.get("searchService");
  const hotels = await searchService.search(city);

  return ctx.json(hotels, 200);
}

export const updateRoomAvailablity = async (ctx) => {
  const { hotelId, count } = ctx.req.query();
  const searchService = ctx.get("searchService");
  await searchService.updateBookedRoom(hotelId, count);

  return ctx.json("successfully updated", 200);
}