/*
 * Copyright (c) 2010 by Cristian Maglie <c.maglie@bug.st>
 * SPI Master library for arduino.
 *
 * This file is free software; you can redistribute it and/or modify
 * it under the terms of either the GNU General Public License version 2
 * or the GNU Lesser General Public License version 2.1, both as
 * published by the Free Software Foundation.
 */

#include "spi.h"

void spi_begin()
{
	// Set direction register for SCK and MOSI pin.
	// MISO pin automatically overrides to INPUT.
	// When the SS pin is set as OUTPUT, it can be used as
	// a general purpose output port (it doesn't influence
	// SPI operations).

	SPI_PORT_DIR |= SPI_BIT_MOSI | SPI_BIT_SCK | SPI_BIT_SS;
	SPI_PORT_DIR &= ~SPI_BIT_MISO;

	SPI_PORT &= ~(SPI_BIT_MOSI | SPI_BIT_SCK);
	SPI_PORT |= SPI_BIT_SS;

	// Warning: if the SS pin ever becomes a LOW INPUT then SPI
	// automatically switches to Slave, so the data direction of
	// the SS pin MUST be kept as OUTPUT.
	SPCR |= _BV(MSTR);
	SPCR |= _BV(SPE);
}

/*
void spi_end()
{
	SPCR &= ~_BV(SPE);
}

void spi_setBitOrder(uint8_t bitOrder)
{
	if(bitOrder == SPI_LSBFIRST) {
		SPCR |= _BV(DORD);
	} else {
		SPCR &= ~(_BV(DORD));
	}
}

void spi_setDataMode(uint8_t mode)
{
	SPCR = (SPCR & ~SPI_MODE_MASK) | mode;
}

void spi_setClockDivider(uint8_t rate)
{
	SPCR = (SPCR & ~SPI_CLOCK_MASK) | (rate & SPI_CLOCK_MASK);
	SPSR = (SPSR & ~SPI_2XCLOCK_MASK) | ((rate >> 2) & SPI_2XCLOCK_MASK);
}

uint8_t spi_transfer(byte _data)
{
	SPDR = _data;
	while (!(SPSR & _BV(SPIF)))
		;
	return SPDR;
}

void spi_attachInterrupt()
{
	SPCR |= _BV(SPIE);
}

void spi_detachInterrupt()
{
	SPCR &= ~_BV(SPIE);
}
*/
