return {
  version = "1.1",
  luaversion = "5.1",
  orientation = "orthogonal",
  width = 11,
  height = 11,
  tilewidth = 60,
  tileheight = 32,
  properties = {},
  tilesets = {
    {
      name = "block",
      firstgid = 1,
      tilewidth = 60,
      tileheight = 32,
      spacing = 0,
      margin = 0,
      image = "../../TiledFiles/block.png",
      imagewidth = 60,
      imageheight = 32,
      properties = {},
      tiles = {}
    }
  },
  layers = {
    {
      type = "tilelayer",
      name = "Tile Layer 1",
      x = 0,
      y = 0,
      width = 11,
      height = 11,
      visible = true,
      opacity = 1,
      properties = {},
      encoding = "lua",
      data = {
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0,
        0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0,
        0, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0,
        0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0,
        0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0
      }
    }
  }
}
